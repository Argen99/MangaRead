package com.geektech.data.remote.api_service

import com.geektech.data.remote.model.*
import retrofit2.http.*

interface MainApiService {

    @POST("token/refresh/")
    suspend fun refreshToken(
        @Body token: String
    ): RefreshTokenDto

    @GET("v1/genre/")
    suspend fun getGenres(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): List<GenresDto>
}