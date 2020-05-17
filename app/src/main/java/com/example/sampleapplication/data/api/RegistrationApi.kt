package com.example.sampleapplication.data.api

import com.example.sampleapplication.data.Empty
import com.example.sampleapplication.data.api.model.request.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {

    @POST("api/user/register")
    suspend fun signUp(@Body request: RegisterRequest): Response<Empty>
}
