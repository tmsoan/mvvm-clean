package com.anos.demo.view.list

import android.util.Log
import androidx.lifecycle.*
import com.anos.demo.di.interactor.InteractorComponentHolder
import com.anos.domain.news.FetchNewsDetailsInteractor
import com.anos.domain.news.FetchNewsInteractor
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

    fun fetchNews() {
        viewModelScope.launch {
            Log.e(TAG, "####### fetchNews")
            fetchNewsInteractor.invoke()?.forEach {
                Log.e(TAG, "${it.title}")
            }
        }
    }

    fun fetchNewsDetails(id: String) {
        viewModelScope.launch {
            Log.e(TAG, "####### fetchNewsDetails")
            fetchNewsDetailsInteractor.invoke(id)?.let {
                Log.e(TAG, "${it.title}")
            }
        }
    }

    private companion object {
        private const val TAG = "NewsListViewModel"
    }
}