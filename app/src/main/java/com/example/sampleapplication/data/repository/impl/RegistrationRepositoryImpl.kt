package com.example.sampleapplication.data.repository.impl

import com.example.sampleapplication.data.Empty
import com.example.sampleapplication.data.Value
import com.example.sampleapplication.data.api.RegistrationApi
import com.example.sampleapplication.data.api.model.request.RegisterRequest
import com.example.sampleapplication.data.repository.BaseRepository
import com.example.sampleapplication.data.repository.interfaze.RegistrationRepository

class RegistrationRepositoryImpl(private val registrationApi: RegistrationApi) : RegistrationRepository, BaseRepository() {

    override suspend fun signUp(login: String, password: String): Value<Empty> = apiCall {
        registrationApi.signUp(RegisterRequest(login, password))
    }
}
