package com.geektech.domain.repositories

import androidx.paging.PagingData
import com.geektech.domain.common.Resource
import com.geektech.domain.model.MangaResult
import kotlinx.coroutines.flow.Flow

interface MangaRepository {

    fun getAllManga(
        type: List<String>?,
        genreTitle: List<String>?,
        enName: String?,
        ruName: String?,
        search: String?
    ): Flow<PagingData<MangaResult>>

    fun getTopManga(
        limit: Int?,
        offset: Int?,
        type: List<String>?,
        genreTitle: List<String>?,
        enName: String?,
        ruName: String?,
        search: String?
    ): Flow<Resource<List<MangaResult>>>

    fun getMangaById(id: String): Flow<Resource<MangaResult>>
}