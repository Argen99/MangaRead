package com.geektech.data.core.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.geektech.data.core.utils.Constants
import com.geektech.domain.common.Resource
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequestList(request: () -> Unit) = flow {
        emit(Resource.Loading())
        try {
            val data = request()
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            val data = request()
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "Unknown error"))
        } catch (illegalStateException: IllegalStateException) {
            emit(Resource.Error("illegalStateException"))
        } catch (jsonSyntaxException: JsonSyntaxException) {
            emit(Resource.Error("Неверное имя пользователя или пароль"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    protected fun <Key : Any, Model : Any> doPagingRequest(
        pagingSource: PagingSource<Key, Model>
    ) = Pager(
        PagingConfig(
            pageSize = Constants.NETWORK_PAGE_SIZE,
            enablePlaceholders = false,
        ), pagingSourceFactory = {
            pagingSource
        }
    ).flow
}
