package com.example.sampleapplication.data.api

import com.example.sampleapplication.data.Empty
import com.example.sampleapplication.data.api.model.request.ProfileEditRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface ProfileApi {

    @PUT("api/user/edit")
    suspend fun edit(@Body request: ProfileEditRequest): Response<Empty>
}
