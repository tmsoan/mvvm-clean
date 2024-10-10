package com.anos.domain.di

import com.anos.domain.repository.RssRepository
import com.anos.domain.rss.GetRssByChannelInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RssInteractorModule {
    @Singleton
    @Provides
    fun provideGetRssByChannelInteractor(
        rssRepository: RssRepository,
    ): GetRssByChannelInteractor {
        return GetRssByChannelInteractor(rssRepository)
    }
}
