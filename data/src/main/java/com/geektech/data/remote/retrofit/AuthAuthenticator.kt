package com.geektech.data.remote.retrofit

import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.data.remote.api_service.MainApiService
import kotlinx.coroutines.runBlocking
import okhttp3.*

class AuthAuthenticator(
    private val apiService: MainApiService,
    private val tokenManager: TokenManager,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            tokenManager.getRefreshToken().let { safeRefreshToken ->
                apiService.refreshToken(safeRefreshToken!!).let { freshToken ->
                    tokenManager.saveAccessToken(freshToken.access)
                    response.request.newBuilder()
                        .authorizationHeader(freshToken.access)
                        .build()
                }
            } ?: kotlin.run {
                null
            }
        }
    }

    private fun Request.Builder.authorizationHeader(accessToken: String): Request.Builder {
        return header(
            "Authorization",
            "Bearer $accessToken"
        )
    }
}