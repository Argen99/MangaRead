package com.geektech.data.remote.api_service

import com.geektech.data.remote.model.MangaResponseDto
import com.geektech.data.remote.model.MangaResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaApiService {

    @GET("v1/manga/")
    suspend fun getAllManga(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("type") type: List<String>?,
        @Query("genre__title") genreTitle: List<String>?,
        @Query("search") search: String?
    ): MangaResponseDto

    @GET("v1/top-manga/")
    suspend fun getTopManga(
        @Query("type") type: List<String>?,
        @Query("genre__title") genreTitle: List<String>?,
        @Query("search") search: String?
    ): List<MangaResultDto>

    @GET("v1/manga/{id}/")
    suspend fun getMangaById(
        @Path("id") id: String
    ): MangaResultDto
}