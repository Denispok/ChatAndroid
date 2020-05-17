package com.example.sampleapplication.data

sealed class Value<out T> {
    data class Success<out T>(val data: T) : Value<T>()
    data class Error(val throwable: Throwable) : Value<Nothing>()
}
