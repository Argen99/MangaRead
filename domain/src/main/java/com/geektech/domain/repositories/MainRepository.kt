package com.geektech.domain.repositories

import androidx.paging.PagingData
import com.geektech.domain.common.Resource
import com.geektech.domain.model.*
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface MainRepository {

    // Manga
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

    // Comments
    fun getCommentsById(id: Int, limit: Int?, offset: Int?): Flow<Resource<List<MangaComments>>>

    // Auth
    fun userRegister(
        username: RequestBody,
        nickname: RequestBody,
        imageFile: RequestBody,
        password: RequestBody,
        imageUri: String
    ): Flow<Resource<User>>

    fun userLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>

    // Genre
    fun getGenres(): Flow<Resource<List<Genres>>>
}