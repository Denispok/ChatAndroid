package com.example.sampleapplication.data.repository.impl

import com.example.sampleapplication.data.Empty
import com.example.sampleapplication.data.Value
import com.example.sampleapplication.data.api.ProfileApi
import com.example.sampleapplication.data.api.model.request.ProfileEditRequest
import com.example.sampleapplication.data.repository.BaseRepository
import com.example.sampleapplication.data.repository.interfaze.ProfileRepository

class ProfileRepositoryImpl(private val profileApi: ProfileApi) : ProfileRepository, BaseRepository() {

    override suspend fun edit(login: String?, password: String?): Value<Empty> = apiCall {
        profileApi.edit(ProfileEditRequest(login, password))
    }
}
