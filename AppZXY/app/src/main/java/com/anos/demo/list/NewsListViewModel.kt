package com.anos.demo.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anos.data.repository.NewsRepositoryImpl
import com.anos.demo.MyApp
import com.anos.domain.news.FetchNewsDetailsInteractor
import com.anos.domain.news.FetchNewsDetailsInteractorImpl
import com.anos.domain.news.FetchNewsInteractor
import com.anos.domain.news.FetchNewsInteractorImpl
import com.anos.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel : ViewModel() {

    init {
        MyApp.appComponent.inject(this)
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