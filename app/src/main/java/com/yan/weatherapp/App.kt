package com.yan.weatherapp

import android.app.Application

class App : Application() {

    lateinit var appContext: App
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}