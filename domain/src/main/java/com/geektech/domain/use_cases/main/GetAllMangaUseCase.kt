package com.geektech.domain.use_cases.main

import com.geektech.domain.repositories.MainRepository

class GetAllMangaUseCase(private val repository: MainRepository) {

    operator fun invoke(
        type: List<String>?,
        genreTitle: List<String>?,
        enName: String?,
        ruName: String?,
        search: String?
    ) = repository.getAllManga(
        type = type,
        genreTitle = genreTitle,
        enName = enName,
        ruName = ruName,
        search = search
    )
}