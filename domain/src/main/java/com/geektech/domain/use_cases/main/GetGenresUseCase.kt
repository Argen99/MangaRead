package com.geektech.domain.use_cases.main

import com.geektech.domain.repositories.MainRepository

class GetGenresUseCase(
    private val repository: MainRepository
) {
    operator fun invoke() = repository.getGenres()
}