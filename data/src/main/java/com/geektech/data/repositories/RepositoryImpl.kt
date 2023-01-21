package com.geektech.data.repositories

import androidx.paging.*
import com.geektech.data.remote.api_service.MangaReadApiService
import com.geektech.data.remote.mappers.toMangaResponse
import com.geektech.data.remote.mappers.toMangaResult
import com.geektech.data.remote.mappers.toUser
import com.geektech.data.remote.mappers.toUserRegisterBodyDto
import com.geektech.data.remote.paging_source.MangaPagingSource
import com.geektech.data.repositories.base.BaseRepository
import com.geektech.domain.common.Resource
import com.geektech.domain.model.MangaResponse
import com.geektech.domain.model.MangaResult
import com.geektech.domain.model.User
import com.geektech.domain.model.UserRegisterBody
import com.geektech.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val apiService: MangaReadApiService) :
    BaseRepository(), Repository {

    override fun getManga(
        type: String?,
        genreTitle: String?,
        enName: String?,
        ruName: String?,
        search: String?,
        page: Int?
    ): Flow<Resource<MangaResponse>> = doRequest {
        apiService.getManga(
            type = type, genreTitle = genreTitle, enName = enName,
            ruName = ruName, search = search, page = page
        ).toMangaResponse()
    }

//    override fun getMangaPg(
//        type: String?, genreTitle: String?, enName: String?,
//        ruName: String?, search: String?
//    ): Flow<PagingData<MangaResult>> {
//        return doPagingRequest(
//            MangaPagingSource(
//                apiService, type, genreTitle, enName, ruName, search
//            )
//        ).map { pagingData ->
//            pagingData.map {
//                it.toMangaResult()
//            }
//        }
//    }

    override fun getTopManga(
        type: String?,
        genreTitle: String?,
        enName: String?,
        ruName: String?,
        search: String?,
        page: Int?
    ): Flow<Resource<MangaResponse>> = doRequest {
        apiService.getTopManga(
            type = type, genreTitle = genreTitle, enName = enName,
            ruName = ruName, search = search, page = page
        ).toMangaResponse()
    }


    override fun getMangaById(id: String): Flow<Resource<MangaResult>> = doRequest {
        apiService.getCapById(id).toMangaResult()
    }

    override fun userRegister(body: UserRegisterBody): Flow<Resource<User>> = doRequest {
        apiService.registerUser(body.toUserRegisterBodyDto()).toUser()
    }
}
