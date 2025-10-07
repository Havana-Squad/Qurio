package com.thechance.qurio.data.util

import com.thechance.qurio.domain.exception.NoInternetException
import com.thechance.qurio.domain.exception.QurioException
import com.thechance.qurio.domain.exception.UnknownException
import okio.IOException
import retrofit2.Response
import java.net.ConnectException

suspend inline fun <T> safeApiCall(
    crossinline execute: suspend () -> Response<T>
): T {
    try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            return body
        } else {
            throw UnknownException(response.errorBody()?.string() ?: "Unknown")
        }
    } catch (e: QurioException) {
        throw e
    } catch (_: IOException) {
        throw NoInternetException()
    } catch (_: Throwable) {
        throw UnknownException()
    }
}