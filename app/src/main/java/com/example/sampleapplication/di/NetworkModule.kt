package com.example.sampleapplication.di

import com.example.sampleapplication.BuildConfig
import com.example.sampleapplication.data.api.AuthApi
import com.example.sampleapplication.data.api.StatusCode
import com.example.sampleapplication.data.api.model.request.RefreshTokenRequest
import com.example.sampleapplication.data.manager.preferences.PreferencesManager
import com.example.sampleapplication.model.exception.NetworkError
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val AUTH = "auth"
    }

    @Singleton
    @Provides
    fun jacksonConverterFactory(): JacksonConverterFactory = JacksonConverterFactory.create(
        ObjectMapper()
            .registerModule(KotlinModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    )

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @Named(AUTH)
    fun okHttpClientAuth(authApi: AuthApi, preferencesManager: PreferencesManager): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val authInterceptor = Interceptor { chain ->
            val jwt = preferencesManager.authPreferences.getJwt()
            var response = chain.proceed(createAuthorizedRequest(chain.request(), jwt))
            when (response.code) {
                StatusCode.UNAUTHORIZED.code -> {
                    val refreshToken = preferencesManager.authPreferences.getRefreshToken()
                    val authResponse = authApi.refreshToken(RefreshTokenRequest(refreshToken)).execute()
                    authResponse.body()?.also { authResponseBody ->
                        preferencesManager.authPreferences.putAuthKeys(
                            authResponseBody.jwt,
                            authResponseBody.refreshToken
                        )
                    } ?: throw NetworkError("Refresh token body is null")

                    val newJwt = preferencesManager.authPreferences.getJwt()
                    response.close()
                    response = chain.proceed(createAuthorizedRequest(chain.request(), newJwt))
                }
            }
            response
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    private fun createAuthorizedRequest(request: Request, jwt: String): Request {
        val requestBuilder = request.newBuilder().header("Authorization", "Bearer $jwt")
        return requestBuilder.build()
    }

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, jacksonConverterFactory: JacksonConverterFactory): Retrofit = Retrofit.Builder()
        .baseUrl("http://" + BuildConfig.SERVER_ADDRESS)
        .client(okHttpClient)
        .addConverterFactory(jacksonConverterFactory)
        .build()

    @Singleton
    @Provides
    @Named(AUTH)
    fun retrofitAuth(@Named(AUTH) okHttpClient: OkHttpClient, jacksonConverterFactory: JacksonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://" + BuildConfig.SERVER_ADDRESS)
            .client(okHttpClient)
            .addConverterFactory(jacksonConverterFactory)
            .build()
}
