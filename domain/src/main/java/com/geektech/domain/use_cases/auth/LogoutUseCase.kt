package com.geektech.domain.use_cases.auth

import com.geektech.domain.repositories.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(refresh: String) =
        repository.logout(refresh = refresh)
}