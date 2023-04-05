package com.geektech.domain.use_cases.manga

import com.geektech.domain.repositories.MangaRepository

class GetMangaByIdUseCase(
    private val repository: MangaRepository
) {

    operator fun invoke(id: String) = repository.getMangaById(id)
}
