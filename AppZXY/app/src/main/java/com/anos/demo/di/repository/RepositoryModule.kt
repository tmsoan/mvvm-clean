package com.anos.demo.di.repository

import com.anos.data.repository.NewsRepositoryImpl
import com.anos.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }
}