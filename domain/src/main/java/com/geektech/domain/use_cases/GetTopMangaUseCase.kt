package com.geektech.domain.use_cases

import com.geektech.domain.repositories.Repository

class GetTopMangaUseCase(private val repository: Repository) {

    operator fun invoke(
        type: String?,
        genreTitle: String?,
        enName: String?,
        ruName: String?,
        search: String?,
        page: Int?
    ) = repository.getTopManga(
        type = type,
        genreTitle = genreTitle,
        enName = enName,
        ruName = ruName,
        search = search,
        page = page
    )
}