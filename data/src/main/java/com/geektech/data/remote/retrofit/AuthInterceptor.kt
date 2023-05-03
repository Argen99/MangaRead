package com.geektech.data.remote.retrofit

import com.geektech.data.local_db.prefs.TokenManager
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.ProtocolException

class AuthInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        if (request.header("Authorization") == null) {
            tokenManager.getAccessToken()?.let { token ->
                builder.addHeader(
                    "Authorization",
                    "Bearer $token"
                )
            }
        }

        return try {
            chain.proceed(builder.build())
        } catch (e: ProtocolException) {
            val emptyBody = "".toResponseBody("text/plain".toMediaTypeOrNull())
            ResponseBody

            return chain.proceed(builder.build())
                .newBuilder()
                .code(200)
                .body(emptyBody)
                .build()
        }
    }
}

