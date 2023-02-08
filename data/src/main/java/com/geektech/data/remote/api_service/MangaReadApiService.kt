package com.geektech.data.remote.api_service

import com.geektech.data.remote.model.*
import okhttp3.RequestBody
import retrofit2.http.*

interface MangaReadApiService {

    @GET("v1/manga/")
    suspend fun getAllManga(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("type") type: List<String>?,
        @Query("genre__title") genreTitle: List<String>?,
        @Query("en_name") enName: String?,
        @Query("ru_name") ruName: String?,
        @Query("search") search: String?
    ): MangaResponseDto

    @GET("v1/top-manga/")
    suspend fun getTopManga(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("type") type: List<String>?,
        @Query("genre__title") genreTitle: List<String>?,
        @Query("en_name") enName: String?,
        @Query("ru_name") ruName: String?,
        @Query("search") search: String?
    ): List<MangaResultDto>

    @GET("v1/manga/{id}/")
    suspend fun getCapById(
        @Path("id") id: String
    ): MangaResultDto

    @Multipart
    @POST("auth/signup/")
    suspend fun userRegister(
        @Part ("username") username: RequestBody,
        @Part ("nickname") nickname: RequestBody,
        @Part ("image_file\"; filename = \"pp.png") imageFile: RequestBody,
        @Part ("password") password: RequestBody
    ) : UserDto

    @POST("auth/signin/")
    @Headers("Content-Type: application/json")
    suspend fun userLogin(
       @Body request: LoginRequestDto
    ) : LoginResponseDto

    // Comments
    @GET("v1/manga/{id}/comments/")
    suspend fun getCommentsById (
        @Path("id") id: Int,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): List<MangaCommentsDto>

    @POST("token/refresh/")
    suspend fun refreshToken(
        @Body token: String
    ) : RefreshTokenDto

    @GET("v1/genre/")
    suspend fun getGenres(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): List<GenresDto>
}