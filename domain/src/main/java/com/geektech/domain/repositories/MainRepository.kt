package com.geektech.domain.repositories

import com.geektech.domain.common.Resource
import com.geektech.domain.model.Genres
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getGenres(): Flow<Resource<List<Genres>>>
}