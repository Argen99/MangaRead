package com.geektech.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geektech.data.core.utils.Constants
import com.geektech.data.remote.api_service.MangaReadApiService
import com.geektech.data.remote.mappers.toMangaResult
import com.geektech.domain.model.MangaResult

class MangaPagingSource(
    private val apiService: MangaReadApiService,
    private val type: List<String>? = null,
    private val genreTitle: List<String>?= null,
    private val en_name: String?= null,
    private val ru_name: String?= null,
    private val search: String?= null
) : PagingSource<Int, MangaResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MangaResult> {

        return try {
            val pageIndex = params.key ?: 0

            val response = apiService.getAllManga(
                limit = params.loadSize, offset = pageIndex, type,
                genreTitle, en_name, ru_name, search)

            val data = response.results.map { it.toMangaResult() }
            val nextPageNumber = if (data.size == params.loadSize)
                pageIndex + params.loadSize / Constants.NETWORK_PAGE_SIZE
            else
                null

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextPageNumber)

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MangaResult>): Int? {
        return null
    }
}