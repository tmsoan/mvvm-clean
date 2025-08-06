package com.anos.data.di

import com.anos.data.repository.RssRepositoryImpl
import com.anos.domain.repository.RssRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsRssRepository(rssApi: RssRepositoryImpl): RssRepository
}
