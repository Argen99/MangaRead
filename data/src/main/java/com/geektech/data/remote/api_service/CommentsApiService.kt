package com.geektech.data.remote.api_service

import com.geektech.data.remote.model.AddCommentResponseDto
import com.geektech.data.remote.model.MangaCommentsDto
import retrofit2.http.*

interface CommentsApiService {

    @POST("v1/manga/{id}/add-comment/")
    @FormUrlEncoded
    suspend fun addComment(
        @Path("id") id: Int,
        @Field("text") comment: String
    ): AddCommentResponseDto

    @GET("v1/manga/{id}/comments/")
    suspend fun getCommentsByMangaId (
        @Path("id") id: Int,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): List<MangaCommentsDto>
}