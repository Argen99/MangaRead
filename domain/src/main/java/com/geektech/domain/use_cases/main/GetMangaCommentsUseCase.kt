package com.geektech.domain.use_cases.main

import com.geektech.domain.repositories.MainRepository

class GetMangaCommentsUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(id: Int, limit: Int?, offset: Int?) =
        repository.getCommentsById(id = id, limit =limit, offset = offset)
}