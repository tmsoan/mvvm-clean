package com.anos.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anos.domain.rss.GetRssByChannelInteractor
import com.anos.model.Feed
import com.anos.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRssByChannelInteractor: GetRssByChannelInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    fun getRssChannels() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    channels = RssConstants.channels,
                    isLoading = false
                )
            }
        }
    }

    fun getFeedByChannel(channel: String, forceUpdate: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            if (!forceUpdate && _uiState.value.feedMap.get(channel)?.articles?.isNotEmpty() == true) {
                _uiState.update {
                    it.copy(
                        feedMap = it.feedMap,
                        isLoading = false
                    )
                }
                return@launch
            }
            getRssByChannelInteractor(channel).let { feed ->
                _uiState.update {
                    it.copy(
                        feedMap = it.feedMap.apply {
                            put(channel, feed)
                        },
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val channels: Map<String, String> = emptyMap(),
    val feedMap: MutableMap<String, Feed> = mutableMapOf()
)