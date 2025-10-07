package com.thechance.qurio.data.util

import com.thechance.qurio.domain.exception.NoInternetException
import com.thechance.qurio.domain.exception.UnknownException
import okio.IOException
import retrofit2.Response
import java.net.ConnectException

suspend inline fun <T> saveApiCall(
    crossinline execute: suspend () -> Response<T>
): T {
    try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            return body
        } else {
            when (response.code()) {
                400 -> throw UnknownException("bad request")
                401 -> throw UnknownException("unauthorized request")
                403 -> throw UnknownException("forbidden request")
                404 -> throw UnknownException("not found")
                429 -> throw UnknownException("too many requests")
                500 -> throw UnknownException("server error")
                503 -> throw UnknownException("service unavailable")
                else -> throw UnknownException()
            }
        }
    } catch (_: ConnectException) {
        throw NoInternetException()
    } catch (_: IOException) {
        throw NoInternetException()
    } catch (_: Throwable) {
        throw UnknownException()
    }
}