package com.anos.demo.view.list

import android.util.Log
import androidx.lifecycle.*
import com.anos.demo.di.interactor.InteractorComponentHolder
import com.anos.domain.entity.NewsItem
import com.anos.domain.news.FetchNewsDetailsInteractor
import com.anos.domain.news.FetchNewsInteractor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel : ViewModel() {

    init {
        InteractorComponentHolder.get().inject(this)
    }

    @Inject
    lateinit var fetchNewsInteractor: FetchNewsInteractor
    @Inject
    lateinit var fetchNewsDetailsInteractor: FetchNewsDetailsInteractor

    private val _lstNewsLive: MutableLiveData<List<NewsItem>> = MutableLiveData()
    val lstNewsLive: LiveData<List<NewsItem>> = _lstNewsLive

    fun fetchNews() {
        viewModelScope.launch {
            Log.e(TAG, "####### fetchNews")
            fetchNewsInteractor.invoke().let {
                delay(2000)
                _lstNewsLive.postValue(it ?: emptyList())
            }
        }
    }

    fun fetchNewsDetails(id: String) {
        viewModelScope.launch {
            Log.e(TAG, "####### fetchNewsDetails")
            fetchNewsDetailsInteractor.invoke(id)?.let {
                Log.w(TAG, ">>> ${it.id} | ${it.title}")
            }
        }
    }

    private companion object {
        private const val TAG = "NewsListViewModel"
    }
}