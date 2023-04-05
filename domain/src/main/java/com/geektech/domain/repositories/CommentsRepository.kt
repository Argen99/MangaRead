package com.geektech.domain.repositories

import com.geektech.domain.common.Resource
import com.geektech.domain.model.AddCommentResponse
import com.geektech.domain.model.MangaComments
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    fun addComment(id: Int, comment: String
    ): Flow<Resource<AddCommentResponse>>

    fun getCommentsById(id: Int, limit: Int?, offset: Int?): Flow<Resource<List<MangaComments>>>
}