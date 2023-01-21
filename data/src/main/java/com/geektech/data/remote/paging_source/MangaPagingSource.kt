package com.geektech.data.remote.paging_source

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geektech.data.remote.api_service.MangaReadApiService
import com.geektech.data.remote.mappers.toMangaResponse
import com.geektech.data.remote.model.MangaResultDto
import com.geektech.domain.model.MangaResult
import retrofit2.HttpException
import java.io.IOException

class MangaPagingSource(
    private val apiService: MangaReadApiService,
    private val type: String? = null,
    private val genreTitle: String?= null,
    private val en_name: String?= null,
    private val ru_name: String?= null,
    private val search: String?= null
//    private val loadAllManga: Boolean
) : PagingSource<Int, MangaResultDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MangaResultDto> {

        return try {
            val page = params.key ?: 1

            val response = apiService.getManga(type, genreTitle, en_name,
                    ru_name, search, page)

            val nextPageNumber = if (response.next == null) {
                null
            } else
                Uri.parse(response.next).getQueryParameter("page")?.toInt()
            return LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MangaResultDto>): Int? {
        return null
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//
//        }
    }
}