package com.geektech.data.remote.api_service

import com.geektech.data.remote.model.MangaResponseDto
import com.geektech.data.remote.model.MangaResultDto
import com.geektech.data.remote.model.UserDto
import com.geektech.data.remote.model.UserRegisterBodyDto
import com.geektech.domain.model.MangaResponse
import retrofit2.Response
import retrofit2.http.*

interface MangaReadApiService {

    @GET("v1/manga/")
    suspend fun getManga(
        @Query("type") type: String?,
        @Query("genre__title") genreTitle: String?,
        @Query("en_name") enName: String?,
        @Query("ru_name") ruName: String?,
        @Query("search") search: String?,
        @Query("page") page: Int?,
    ): MangaResponseDto

    @GET("v1/manga/{id}/")
    suspend fun getCapById(
        @Path("id") id: String
    ): MangaResultDto

    @GET("v1/top-manga/")
    suspend fun getTopManga(
        @Query("type") type: String?,
        @Query("genre__title") genreTitle: String?,
        @Query("en_name") enName: String?,
        @Query("ru_name") ruName: String?,
        @Query("search") search: String?,
        @Query("page") page: Int?,
    ): MangaResponseDto

    @POST("auth/signup/")
    suspend fun registerUser(
        @Body userRegisterRequestDto: UserRegisterBodyDto
    ): UserDto
}