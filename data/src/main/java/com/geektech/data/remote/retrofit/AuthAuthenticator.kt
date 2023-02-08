package com.geektech.data.remote.retrofit

import com.geektech.data.BuildConfig.BASE_URL
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.data.remote.api_service.MangaReadApiService
import com.geektech.data.remote.model.RefreshTokenDto
import com.geektech.domain.model.LoginResponse
import com.geektech.domain.model.RefreshToken
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthAuthenticator(
    private val apiService: MangaReadApiService,
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