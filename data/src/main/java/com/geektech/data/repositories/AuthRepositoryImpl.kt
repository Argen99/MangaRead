package com.geektech.data.repositories

import com.geektech.data.core.base.BaseRepository
import com.geektech.data.remote.api_service.AuthApiService
import com.geektech.data.remote.api_service.MangaReadApiService
import com.geektech.data.remote.mappers.toAddCommentResponse
import com.geektech.data.remote.mappers.toLoginRequestDto
import com.geektech.data.remote.mappers.toUser
import com.geektech.data.remote.mappers.toLoginResponse
import com.geektech.domain.common.Resource
import com.geektech.domain.model.AddCommentResponse
import com.geektech.domain.model.LoginRequest
import com.geektech.domain.model.User
import com.geektech.domain.model.LoginResponse
import com.geektech.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

class AuthRepositoryImpl (
    private val apiService: AuthApiService
) : BaseRepository(), AuthRepository{

    override fun addComment(id: Int, comment: String): Flow<Resource<AddCommentResponse>> = doRequest {
        apiService.addComment(id = id , comment = comment).toAddCommentResponse()
    }

    override fun logout(refresh: String): Flow<Resource<Unit>> = doRequest {
        apiService.logout(refresh)
    }
}