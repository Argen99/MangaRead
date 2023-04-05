package com.geektech.data.remote.api_service

import com.geektech.data.remote.model.LoginRequestDto
import com.geektech.data.remote.model.LoginResponseDto
import com.geektech.data.remote.model.UserDto
import okhttp3.RequestBody
import retrofit2.http.*

interface AuthApiService {

    @Multipart
    @POST("auth/signup/")
    suspend fun userRegister(
        @Part("username") username: RequestBody,
        @Part("nickname") nickname: RequestBody,
        @Part("image_file\"; filename = \"pp.png") imageFile: RequestBody,
        @Part("password") password: RequestBody,
    ): UserDto

    @POST("auth/signin/")
    @Headers("Content-Type: application/json")
    suspend fun userLogin(
        @Body request: LoginRequestDto
    ): LoginResponseDto

    @POST("auth/logout/")
    @FormUrlEncoded
    suspend fun logout(
        @Field("refresh") refreshToken: String
    )
}