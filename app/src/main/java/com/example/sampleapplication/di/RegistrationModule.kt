package com.example.sampleapplication.di

import com.example.sampleapplication.data.api.RegistrationApi
import com.example.sampleapplication.data.repository.impl.RegistrationRepositoryImpl
import com.example.sampleapplication.data.repository.interfaze.RegistrationRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RegistrationModule {

    @Singleton
    @Provides
    fun registrationRepository(registrationApi: RegistrationApi): RegistrationRepository = RegistrationRepositoryImpl(registrationApi)

    @Singleton
    @Provides
    fun registrationApi(retrofit: Retrofit): RegistrationApi = retrofit.create(RegistrationApi::class.java)
}
