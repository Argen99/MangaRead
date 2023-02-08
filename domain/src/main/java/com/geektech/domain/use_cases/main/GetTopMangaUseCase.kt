package com.geektech.domain.use_cases.main

import com.geektech.domain.repositories.MainRepository

class GetTopMangaUseCase(private val repository: MainRepository) {

    operator fun invoke(
        limit: Int?,
        offset: Int?,
        type: List<String>?,
        genreTitle: List<String>?,
        enName: String?,
        ruName: String?,
        search: String?
    ) = repository.getTopManga(
        limit =limit,
        offset = offset,
        type = type,
        genreTitle = genreTitle,
        enName = enName,
        ruName = ruName,
        search = search
    )
}