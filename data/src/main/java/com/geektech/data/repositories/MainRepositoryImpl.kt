package com.geektech.data.repositories

import com.geektech.data.core.base.BaseRepository
import com.geektech.data.remote.api_service.MainApiService
import com.geektech.data.remote.mappers.toModel
import com.geektech.domain.common.Resource
import com.geektech.domain.model.Genres
import com.geektech.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: MainApiService
) : BaseRepository(), MainRepository {

    override fun getGenres(): Flow<Resource<List<Genres>>> = doRequest {
        apiService.getGenres(null, null).map { it.toModel() }
    }
}
