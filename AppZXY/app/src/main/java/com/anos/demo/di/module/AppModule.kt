package com.anos.demo.di.module

import com.anos.demo.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val app: MyApp
) {

    @Provides
    @Singleton
    fun provideApplication() = app
}