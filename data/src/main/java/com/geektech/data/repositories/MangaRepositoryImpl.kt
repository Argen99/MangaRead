package com.geektech.data.repositories

import androidx.paging.PagingData
import com.geektech.data.core.base.BaseRepository
import com.geektech.data.remote.api_service.MangaApiService
import com.geektech.data.remote.mappers.toModel
import com.geektech.data.remote.paging_source.MangaPagingSource
import com.geektech.domain.common.Resource
import com.geektech.domain.model.MangaResult
import com.geektech.domain.repositories.MangaRepository
import kotlinx.coroutines.flow.Flow

class MangaRepositoryImpl(
    private val apiService: MangaApiService
) : BaseRepository(), MangaRepository {

    override fun getAllManga(
        type: List<String>?,
        genreTitle: List<String>?,
        search: String?
    ): Flow<PagingData<MangaResult>> = doPagingRequest(
        MangaPagingSource(
            apiService, type, genreTitle, search
        )
    )

    override fun getTopManga(
        type: List<String>?,
        genreTitle: List<String>?,
        search: String?
    ): Flow<Resource<List<MangaResult>>> = doRequest {
        apiService.getTopManga(
            type = type, genreTitle = genreTitle, search = search
        ).map { it.toModel() }
    }

    override fun getMangaById(id: String): Flow<Resource<MangaResult>> = doRequest {
        apiService.getMangaById(id = id).toModel()
    }
}