package com.geektech.domain.use_cases

import com.geektech.domain.repositories.Repository

class GetMangaByIdUseCase(private val repository: Repository) {

    operator fun invoke(id: String) = repository.getMangaById(id)
}
