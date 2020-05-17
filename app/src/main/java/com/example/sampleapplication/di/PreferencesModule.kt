package com.example.sampleapplication.di

import com.example.sampleapplication.App
import com.example.sampleapplication.data.manager.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun preferencesManager(): PreferencesManager = PreferencesManager(App.appContext)
}
