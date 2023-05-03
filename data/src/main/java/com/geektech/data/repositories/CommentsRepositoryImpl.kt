package com.geektech.data.repositories

import com.geektech.data.core.base.BaseRepository
import com.geektech.data.remote.api_service.CommentsApiService
import com.geektech.data.remote.mappers.toModel
import com.geektech.domain.common.Resource
import com.geektech.domain.model.AddCommentResponse
import com.geektech.domain.model.MangaComments
import com.geektech.domain.repositories.CommentsRepository
import kotlinx.coroutines.flow.Flow

class CommentsRepositoryImpl(
    private val apiService: CommentsApiService
) : BaseRepository(), CommentsRepository {


    override fun addComment(id: Int, comment: String): Flow<Resource<AddCommentResponse>> =
        doRequest {
            apiService.addComment(id = id, comment = comment).toModel()
        }

    override fun getCommentsById(
        id: Int,
        limit: Int?,
        offset: Int?
    ): Flow<Resource<List<MangaComments>>> = doRequest {
        apiService.getCommentsByMangaId(id, limit, offset).map { it.toModel() }
    }
}