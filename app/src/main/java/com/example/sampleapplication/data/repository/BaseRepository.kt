package com.example.sampleapplication.data.repository

import com.example.sampleapplication.data.Value
import com.example.sampleapplication.data.api.StatusCode
import com.example.sampleapplication.model.exception.NetworkError
import com.example.sampleapplication.model.exception.NotFoundException
import com.example.sampleapplication.model.exception.UnauthorizedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRepository {

    suspend fun <T> apiCall(call: suspend () -> Response<T>): Value<T> {
        val response = try {
            withContext(Dispatchers.IO) { call.invoke() }
        } catch (throwable: Throwable) {
            return Value.Error(throwable)
        }

        if (response.isSuccessful) {
            return response.body()?.let {
                Value.Success(it)
            } ?: Value.Error(NetworkError("Response body is null"))
        }

        return Value.Error(parseError(response))
    }

    private fun <T> parseError(response: Response<T>): Throwable {
        return when (response.code()) {
            StatusCode.UNAUTHORIZED.code -> UnauthorizedException()
            StatusCode.NOT_FOUND.code -> NotFoundException()
            else -> NetworkError("Unknown error code")
        }
    }
}
