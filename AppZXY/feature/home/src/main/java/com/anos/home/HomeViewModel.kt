package com.anos.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anos.domain.rss.GetRssByChannelUseCase
import com.anos.home.constant.RssConstants
import com.anos.model.Feed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRssByChannelUseCase: GetRssByChannelUseCase
) : ViewModel() {

    private val _channels = MutableStateFlow(RssConstants.channels)
    val channels: StateFlow<Map<String, String>> = _channels

    private val _selectedChannel = MutableStateFlow<String?>(null)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent = _errorEvent.asSharedFlow()

    init {
        // Observe selected channel changes and auto-fetch RSS
        viewModelScope.launch {
            _selectedChannel.collectLatest { channel ->
                channel?.let {
                    getFeedByChannel(channel, forceUpdate = false)
                }
            }
        }
    }

    fun setSelectedChannel(channel: String) {
        _selectedChannel.value = channel
    }

    fun refreshSelectedChannel() {
        _selectedChannel.value?.let { channel ->
            getFeedByChannel(channel, forceUpdate = true)
        }
    }

    private fun getFeedByChannel(channel: String, forceUpdate: Boolean = false) {
        viewModelScope.launch {
            val currentFeed = _uiState.value.feedMap[channel]
            if (!forceUpdate && currentFeed?.articles?.isNotEmpty() == true) {
                return@launch
            }
            _uiState.update { it.copy(forceLoading = forceUpdate) }

            runCatching {
                val feed = getRssByChannelUseCase(channel)
                _uiState.update {
                    it.copy(
                        feedMap = it.feedMap.toMutableMap().apply { put(channel, feed) },
                        forceLoading = false,
                    )
                }
            }.onFailure { throwable ->
                _uiState.update { it.copy(forceLoading = false) }
                _errorEvent.emit(throwable.message ?: "An error occurred while fetching feed.")
            }
        }
    }
}

data class HomeUiState(
    val forceLoading: Boolean = false,
    val feedMap: MutableMap<String, Feed> = mutableMapOf()
)