package com.example.sampleapplication.di

import com.example.sampleapplication.data.api.ProfileApi
import com.example.sampleapplication.data.repository.impl.ProfileRepositoryImpl
import com.example.sampleapplication.data.repository.interfaze.ProfileRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ProfileModule {

    @Singleton
    @Provides
    fun profileRepository(profileApi: ProfileApi): ProfileRepository = ProfileRepositoryImpl(profileApi)

    @Singleton
    @Provides
    fun profileApi(@Named(NetworkModule.AUTH) retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)
}
