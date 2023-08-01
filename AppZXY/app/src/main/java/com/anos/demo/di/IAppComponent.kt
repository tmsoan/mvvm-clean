package com.anos.demo.di

import com.anos.demo.MyApp
import com.anos.demo.di.module.AppModule
import com.anos.demo.di.module.InteractorModule
import com.anos.demo.di.module.RepositoryModule
import com.anos.demo.list.NewsListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RepositoryModule::class,
    InteractorModule::class,
])
interface IAppComponent {
    fun inject(app: MyApp)
    fun inject(app: NewsListViewModel)
}