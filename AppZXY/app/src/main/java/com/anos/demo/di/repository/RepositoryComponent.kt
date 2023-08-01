package com.anos.demo.di.repository

import com.anos.domain.repository.NewsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface RepositoryComponent {
    fun provideNewsRepository(): NewsRepository
}

object RepositoryComponentHolder {
    fun get(): RepositoryComponent {
        return DaggerRepositoryComponent.builder()
            .build()
    }
}