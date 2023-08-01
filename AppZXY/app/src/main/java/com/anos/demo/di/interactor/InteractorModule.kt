package com.anos.demo.di.interactor

import com.anos.domain.news.FetchNewsDetailsInteractor
import com.anos.domain.news.FetchNewsDetailsInteractorImpl
import com.anos.domain.news.FetchNewsInteractor
import com.anos.domain.news.FetchNewsInteractorImpl
import com.anos.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun provideFetchNewsInteractor(newsRepository: NewsRepository): FetchNewsInteractor {
        return FetchNewsInteractorImpl(newsRepository)
    }

    @Provides
    fun provideFetchNewsDetailsInteractor(newsRepository: NewsRepository): FetchNewsDetailsInteractor {
        return FetchNewsDetailsInteractorImpl(newsRepository)
    }
}