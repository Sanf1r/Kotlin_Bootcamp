package com.example.exercise1

import android.app.Application
import android.content.Context
import com.example.exercise1.di.AppComponent
import com.example.exercise1.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
