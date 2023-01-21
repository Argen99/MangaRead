package com.geektech.domain.use_cases

import com.geektech.domain.repositories.Repository

class GetAllMangaUseCase(private val repository: Repository) {

//    operator fun invoke(
//        type: String?,
//        genreTitle: String?,
//        enName: String?,
//        ruName: String?,
//        search: String?
//    ) = repository.getMangaPg(
//        type = type,
//        genreTitle = genreTitle,
//        enName = enName,
//        ruName = ruName,
//        search = search
//    )
    operator fun invoke(
        type: String?,
        genreTitle: String?,
        enName: String?,
        ruName: String?,
        search: String?,
        page: Int?
    ) = repository.getManga(
        type = type,
        genreTitle = genreTitle,
        enName = enName,
        ruName = ruName,
        search = search,
        page = page
    )
}