package com.geektech.data.remote.api_service

import com.geektech.data.remote.model.AddCommentResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {

    @POST("v1/manga/{id}/add-comment/")
    @FormUrlEncoded
    suspend fun addComment(
        @Path("id") id: Int,
        @Field("text") comment: String
    ): AddCommentResponseDto

    @POST("auth/logout/")
    @FormUrlEncoded
    suspend fun logout(
        @Field("refresh") refreshToken: String
    )
}