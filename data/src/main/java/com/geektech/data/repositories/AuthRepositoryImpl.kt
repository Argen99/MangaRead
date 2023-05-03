package com.geektech.data.repositories

import com.geektech.data.core.base.BaseRepository
import com.geektech.data.remote.api_service.AuthApiService
import com.geektech.data.remote.mappers.toDto
import com.geektech.data.remote.mappers.toModel
import com.geektech.domain.common.Resource
import com.geektech.domain.model.LoginRequest
import com.geektech.domain.model.LoginResponse
import com.geektech.domain.model.User
import com.geektech.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

class AuthRepositoryImpl(
    private val apiService: AuthApiService
) : BaseRepository(), AuthRepository {

    override fun userRegister(
        username: RequestBody,
        nickname: RequestBody,
        imageFile: RequestBody,
        password: RequestBody
    ): Flow<Resource<User>> = doRequest {
        apiService.userRegister(
            username = username, nickname = nickname,
            imageFile = imageFile, password = password
        ).toModel()
    }

    override fun userLogin(
        loginRequest: LoginRequest
    ): Flow<Resource<LoginResponse>> = doRequest {
        apiService.userLogin(loginRequest.toDto()).toModel()
    }

    override fun logout(refresh: String): Flow<Resource<Unit>> = doRequest {
        apiService.logout(refresh)
    }
}