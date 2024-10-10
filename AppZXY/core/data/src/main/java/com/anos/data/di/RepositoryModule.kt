package com.anos.data.di

import com.anos.data.repository.RssRepositoryImpl
import com.anos.domain.repository.RssRepository
import com.anos.network.rest.RssApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRssRepository(rssApi: RssApi): RssRepository {
        return RssRepositoryImpl(rssApi)
    }
}
