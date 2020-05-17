package com.example.sampleapplication.data.repository.interfaze

import com.example.sampleapplication.data.Empty
import com.example.sampleapplication.data.Value

interface ProfileRepository {

    suspend fun edit(login: String?, password: String?): Value<Empty>
}
