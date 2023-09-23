package com.dmendanyo.domain.extensions

import com.dmendanyo.domain.models.Error
import retrofit2.HttpException
import java.io.IOException

fun Throwable.mapToError(): Error =
    when (this) {
        is IOException -> Error.Connectivity
        is HttpException -> Error.Server(code())
        else -> Error.Unknown(message ?: "")
    }

suspend fun <T> safeCall(block: suspend () -> T): Result<T> =
    try {
        val result = block.invoke()
        Result.success(result)
    } catch (exception: Exception) {
        Result.failure(exception)
    }
