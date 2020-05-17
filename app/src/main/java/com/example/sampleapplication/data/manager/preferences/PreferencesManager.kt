package com.example.sampleapplication.data.manager.preferences

import android.content.Context

class PreferencesManager(private val appContext: Context) {

    val authPreferences by lazy { AuthPreferences(appContext) }
}
