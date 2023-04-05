package com.geektech.domain.repositories

import com.geektech.domain.common.Resource
import com.geektech.domain.model.AddCommentResponse
import com.geektech.domain.model.LoginRequest
import com.geektech.domain.model.LoginResponse
import com.geektech.domain.model.User
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface AuthRepository {

    fun userRegister(
        username: RequestBody,
        nickname: RequestBody,
        imageFile: RequestBody,
        password: RequestBody
    ): Flow<Resource<User>>

    fun userLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>

    fun logout(refresh: String): Flow<Resource<Unit>>
}