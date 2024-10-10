package com.anos.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anos.domain.rss.GetRssByChannelInteractor
import com.anos.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRssByChannelInteractor: GetRssByChannelInteractor
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _uiState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<String>>> = _uiState

    fun fetchHotNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getRssByChannelInteractor("the-gioi").let {
                _uiState.value = UiState.Success(it.articles.map { it.title.orEmpty() })
            }
        }
    }
}