package com.example.sampleapplication.model.exception

open class NetworkError(message: String? = null) : Exception(message)

class NotFoundException(message: String? = null) : NetworkError(message)

class UnauthorizedException(message: String? = null) : NetworkError(message)
