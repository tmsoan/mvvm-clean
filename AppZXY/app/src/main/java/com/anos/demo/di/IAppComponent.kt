package com.anos.demo.di

import com.anos.demo.MyApp
import com.anos.demo.di.module.AppModule
import com.anos.demo.di.repository.RepositoryModule
import com.anos.domain.repository.NewsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
])
interface IAppComponent {
    fun inject(app: MyApp)
}

object IAppComponentHolder {
    fun get(app: MyApp): IAppComponent {
        return DaggerIAppComponent.builder()
            .appModule(AppModule(app))
            .build()
    }
}