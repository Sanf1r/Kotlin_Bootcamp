package com.example.app

import android.app.Application
import com.example.app.di.AppComponent
import com.example.app.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

