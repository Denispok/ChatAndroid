package com.example.sampleapplication.data.api

import com.example.sampleapplication.data.api.model.request.AuthRequest
import com.example.sampleapplication.data.api.model.request.RefreshTokenRequest
import com.example.sampleapplication.data.api.model.response.AuthResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/login")
    suspend fun signIn(@Body request: AuthRequest): Response<AuthResponse>

    @POST("api/auth/token")
    fun refreshToken(@Body request: RefreshTokenRequest): Call<AuthResponse>
}
