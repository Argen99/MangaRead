package com.geektech.domain.use_cases.main

import com.geektech.domain.repositories.MainRepository

class GetMangaByIdUseCase(private val repository: MainRepository) {

    operator fun invoke(id: String) = repository.getMangaById(id)
}
