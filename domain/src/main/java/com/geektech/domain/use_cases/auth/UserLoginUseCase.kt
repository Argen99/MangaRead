package com.geektech.domain.use_cases.auth

import com.geektech.domain.model.LoginRequest
import com.geektech.domain.repositories.AuthRepository
import com.geektech.domain.repositories.MainRepository
import okhttp3.RequestBody

class UserLoginUseCase(
    private val repository: AuthRepository,
) {
    operator fun invoke(loginRequest: LoginRequest) =
        repository.userLogin(loginRequest)
}