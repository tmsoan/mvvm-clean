package com.anos.demo.di.module

import com.anos.data.repository.NewsRepositoryImpl
import com.anos.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }
}