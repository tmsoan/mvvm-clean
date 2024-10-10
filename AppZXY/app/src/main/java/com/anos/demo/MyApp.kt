package com.anos.demo

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun getFuncInApp() {
        Log.e(MyApp::class.simpleName, "getFuncInApp >>")
    }

    companion object {
    }
}