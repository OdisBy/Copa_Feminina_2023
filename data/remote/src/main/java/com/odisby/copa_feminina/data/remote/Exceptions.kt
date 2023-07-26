package com.odisby.copa_feminina.data.remote

sealed class HttpException(
    message: String? = null,
    cause: Throwable? = null
) : Throwable(message, cause)
class NotFoundException(message: String?): HttpException(message)
class UnexpectedException : HttpException()
