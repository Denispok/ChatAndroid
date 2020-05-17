package com.example.sampleapplication.di

import com.example.sampleapplication.App
import com.example.sampleapplication.data.manager.StringManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResourceModule {

    @Singleton
    @Provides
    fun stringManager(): StringManager = StringManager(App.appContext)
}
