package com.example.sampleapplication.di

import com.example.sampleapplication.data.api.AuthApi
import com.example.sampleapplication.data.manager.preferences.PreferencesManager
import com.example.sampleapplication.data.repository.impl.AuthRepositoryImpl
import com.example.sampleapplication.data.repository.interfaze.AuthRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AuthModule {

    @Singleton
    @Provides
    fun authRepository(authApi: AuthApi, preferencesManager: PreferencesManager): AuthRepository =
        AuthRepositoryImpl(authApi, preferencesManager)

    @Singleton
    @Provides
    fun authApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}
