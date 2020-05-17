package com.example.sampleapplication

import android.app.Application
import android.content.Context
import com.example.sampleapplication.di.AppComponent
import com.example.sampleapplication.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        appComponent = DaggerAppComponent.create()
    }
}
