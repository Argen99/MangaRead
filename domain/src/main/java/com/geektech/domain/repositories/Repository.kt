package com.geektech.domain.repositories

import androidx.paging.PagingData
import com.geektech.domain.common.Resource
import com.geektech.domain.model.MangaResponse
import com.geektech.domain.model.MangaResult
import com.geektech.domain.model.User
import com.geektech.domain.model.UserRegisterBody
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getManga(
        type: String?,
        genreTitle: String?,
        enName: String?,
        ruName: String?,
        search: String?,
        page: Int?
    ): Flow<Resource<MangaResponse>>

//    fun getMangaPg(
//        type: String?,
//        genreTitle: String?,
//        enName: String?,
//        ruName: String?,
//        search: String?,
//    ): Flow<PagingData<MangaResult>>

    fun getTopManga(
        type: String?,
        genreTitle: String?,
        enName: String?,
        ruName: String?,
        search: String?,
        page: Int?
    ): Flow<Resource<MangaResponse>>

    fun getMangaById(id: String): Flow<Resource<MangaResult>>

    fun userRegister(body: UserRegisterBody): Flow<Resource<User>>
}