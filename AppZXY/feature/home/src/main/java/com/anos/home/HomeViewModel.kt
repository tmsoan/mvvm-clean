package com.anos.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anos.common.AppDispatchers
import com.anos.common.Dispatcher
import com.anos.common.event.GlobalEvent
import com.anos.common.event.GlobalEventBus
import com.anos.domain.rss.GetRssByChannelUseCase
import com.anos.home.constant.RssConstants
import com.anos.model.Feed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.plus

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRssByChannelUseCase: GetRssByChannelUseCase,
    private val globalEventBus: GlobalEventBus,
) : ViewModel() {

    private val _rssChannels = MutableStateFlow(RssConstants.channels)
    val rssChannels: StateFlow<Map<String, String>> = _rssChannels

    private val _selectedRssChannel = MutableStateFlow<String?>(null)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent = _errorEvent.asSharedFlow()

    init {
        // Observe selected channel changes and auto-fetch RSS
        viewModelScope.launch {
            _selectedRssChannel.collectLatest { channel ->
                channel?.let {
                    getFeedByChannel(channel, forceUpdate = false)
                }
            }
        }
    }

    fun triggerEventBus(event: GlobalEvent) {
        globalEventBus.emit(event)
    }

    fun setSelectedChannel(channel: String) {
        _selectedRssChannel.value = channel
    }

    fun refreshSelectedChannel() {
        _selectedRssChannel.value?.let { channel ->
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

            /** Impl with normal func */
            /*runCatching {
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
            }*/

            /** Impl with Flow */
            getRssByChannelUseCase.invokeWithFlow(channel)
                .catch { throwable ->
                    _uiState.update { it.copy(forceLoading = false) }
                    _errorEvent.emit(throwable.message ?: "An error occurred while fetching feed.")
                }.collect { feed ->
                    _uiState.update {
                        it.copy(
                            feedMap = it.feedMap.toMutableMap().apply { put(channel, feed) },
                            forceLoading = false,
                        )
                    }
                }
        }
    }
}

data class HomeUiState(
    val forceLoading: Boolean = false,
    val feedMap: MutableMap<String, Feed> = mutableMapOf()
)