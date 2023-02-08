package com.geektech.domain.repositories

import com.geektech.domain.common.Resource
import com.geektech.domain.model.AddCommentResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun addComment(id: Int, comment: String
    ): Flow<Resource<AddCommentResponse>>

    fun logout(refresh: String): Flow<Resource<Unit>>
}