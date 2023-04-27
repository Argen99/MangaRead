package com.geektech.domain.use_cases.manga

import com.geektech.domain.repositories.MangaRepository

class GetTopMangaUseCase(private val repository: MangaRepository) {

    operator fun invoke(
        type: List<String>?,
        genreTitle: List<String>?,
        search: String?
    ) = repository.getTopManga(
        type = type,
        genreTitle = genreTitle,
        search = search
    )
}