package com.geektech.data.repositories

import androidx.paging.PagingData
import com.geektech.data.core.base.BaseRepository
import com.geektech.data.remote.api_service.MangaReadApiService
import com.geektech.data.remote.mappers.*
import com.geektech.data.remote.paging_source.MangaPagingSource
import com.geektech.domain.common.Resource
import com.geektech.domain.model.*
import com.geektech.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

class MainRepositoryImpl(
    private val apiService: MangaReadApiService
) :
    BaseRepository(), MainRepository {

    // Manga
    override fun getAllManga(
        type: List<String>?,
        genreTitle: List<String>?,
        enName: String?,
        ruName: String?,
        search: String?
    ): Flow<PagingData<MangaResult>> = doPagingRequest(
        MangaPagingSource(
            apiService, type, genreTitle, enName, ruName, search
        )
    )

    override fun getTopManga(
        limit: Int?,
        offset: Int?,
        type: List<String>?,
        genreTitle: List<String>?,
        enName: String?,
        ruName: String?,
        search: String?
    ): Flow<Resource<List<MangaResult>>> = doRequestList {
        apiService.getTopManga(
            limit = limit, offset = offset, type = type, genreTitle = genreTitle,
            enName = enName, ruName = ruName, search = search
        ).map { it.toMangaResult() }
    }


    override fun getMangaById(id: String): Flow<Resource<MangaResult>> = doRequest {
        apiService.getCapById(id = id).toMangaResult()
    }

    // Comments
    override fun getCommentsById(
        id: Int,
        limit: Int?,
        offset: Int?
    ): Flow<Resource<List<MangaComments>>> = doRequestList {
        apiService.getCommentsById(id, limit, offset).map { it.toMangaComments() }
    }

    // Auth
    override fun userRegister(
        username: RequestBody,
        nickname: RequestBody,
        imageFile: RequestBody,
        password: RequestBody,
        imageUri: String
    ): Flow<Resource<User>> = doRequest {
        apiService.userRegister(
            username = username, nickname = nickname,
            imageFile = imageFile, password = password
        ).toUser().apply {
        }

    }

    override fun userLogin(
        loginRequest: LoginRequest
    ): Flow<Resource<LoginResponse>> = doRequest {
        apiService.userLogin(loginRequest.toLoginRequestDto()).toLoginResponse()
    }

    override fun getGenres(): Flow<Resource<List<Genres>>> = doRequestList{
        apiService.getGenres(null, null).map { it.toGenres() }
    }
}
