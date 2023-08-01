package com.anos.demo

import android.app.Application
import android.util.Log
import com.anos.demo.di.IAppComponent
import com.anos.demo.di.IAppComponentHolder

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppComponents()
    }

    private fun initAppComponents() {
        appComponent = IAppComponentHolder.get(this)
    }

    fun getFuncInApp() {
        Log.e(MyApp::class.simpleName, "getFuncInApp >>")
    }

    companion object {
        lateinit var appComponent: IAppComponent
    }
}