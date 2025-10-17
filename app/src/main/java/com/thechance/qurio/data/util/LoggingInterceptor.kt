package com.thechance.qurio.data.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d(TAG, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        Log.d(TAG, "REQUEST")
        Log.d(TAG, "URL: ${request.url}")
        Log.d(TAG, "Method: ${request.method}")
        Log.d(TAG, "Headers: ${request.headers}")

        request.body?.let { body ->
            Log.d(TAG, "Body: Content-Type=${body.contentType()}, Length=${body.contentLength()}")
        }

        val startTime = System.currentTimeMillis()
        val response = chain.proceed(request)
        val duration = System.currentTimeMillis() - startTime

        Log.d(TAG, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        Log.d(TAG, "RESPONSE")
        Log.d(TAG, "URL: ${response.request.url}")
        Log.d(TAG, "Code: ${response.code} ${response.message}")
        Log.d(TAG, "Duration: ${duration}ms")
        Log.d(TAG, "Headers: ${response.headers}")

        val responseBody = response.body
        val source = responseBody?.source()
        source?.request(Long.MAX_VALUE)
        val buffer = source?.buffer

        buffer?.clone()?.readString(Charsets.UTF_8)?.let { bodyString ->
            Log.d(TAG, "Body: $bodyString")
        }

        Log.d(TAG, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")

        return response
    }

    companion object {
        private const val TAG = "API_LOG"
    }
}