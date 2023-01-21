package com.geektech.data.repositories.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.geektech.data.remote.paging_source.MangaPagingSource
import com.geektech.domain.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            val data = request()
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    protected fun <Key: Any, Model : Any> doPagingRequest(
        pagingSource: PagingSource<Key, Model>
    ) = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 10,
            enablePlaceholders = false,
            initialLoadSize = 10 * 3,
            maxSize = Int.MAX_VALUE,
            jumpThreshold = Int.MIN_VALUE
        ), pagingSourceFactory = {
            pagingSource
        }
    ).flow
}
