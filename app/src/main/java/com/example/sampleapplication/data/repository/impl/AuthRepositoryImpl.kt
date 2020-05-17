package com.example.sampleapplication.data.repository.impl

import com.example.sampleapplication.data.Value
import com.example.sampleapplication.data.api.AuthApi
import com.example.sampleapplication.data.api.model.request.AuthRequest
import com.example.sampleapplication.data.api.model.response.AuthResponse
import com.example.sampleapplication.data.manager.preferences.PreferencesManager
import com.example.sampleapplication.data.repository.BaseRepository
import com.example.sampleapplication.data.repository.interfaze.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val preferencesManager: PreferencesManager
) : AuthRepository, BaseRepository() {

    override suspend fun signIn(username: String, password: String): Value<AuthResponse> {
        val response = apiCall { authApi.signIn(AuthRequest(username, password)) }
        if (response is Value.Success) {
            preferencesManager.authPreferences.putAuthKeys(
                response.data.jwt,
                response.data.refreshToken
            )
        }
        return response
    }
}
