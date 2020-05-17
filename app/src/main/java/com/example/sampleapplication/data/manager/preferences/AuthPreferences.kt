package com.example.sampleapplication.data.manager.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class AuthPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        sharedPreferences = EncryptedSharedPreferences.create(
            PreferencesFiles.AUTH.filename,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun putAuthKeys(jwt: String, refreshToken: String) {
        sharedPreferences.edit {
            putString("jwt", jwt)
            putString("refresh_token", refreshToken)
        }
    }

    fun getJwt(): String = sharedPreferences.getString("jwt", "")!!

    fun getRefreshToken(): String = sharedPreferences.getString("refresh_token", "")!!
}
