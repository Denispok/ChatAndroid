package com.example.sampleapplication.data.repository.interfaze

import com.example.sampleapplication.data.Value
import com.example.sampleapplication.data.api.model.response.AuthResponse

interface AuthRepository {

    suspend fun signIn(username: String, password: String): Value<AuthResponse>
}
