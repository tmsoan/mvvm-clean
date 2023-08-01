package com.anos.demo

import android.app.Application
import com.anos.demo.di.DaggerIAppComponent
import com.anos.demo.di.IAppComponent
import com.anos.demo.di.module.AppModule
import com.anos.demo.di.module.InteractorModule
import com.anos.demo.di.module.RepositoryModule

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppComponents()
    }

    private fun initAppComponents() {
        appComponent = DaggerIAppComponent.builder()
            .appModule(AppModule(this))
            .repositoryModule(RepositoryModule())
            .interactorModule(InteractorModule())
            .build()
    }

    companion object {
        lateinit var appComponent: IAppComponent
    }
}