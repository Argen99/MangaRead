package com.geektech.domain.use_cases.main

import com.geektech.domain.model.LoginRequest
import com.geektech.domain.repositories.AuthRepository
import com.geektech.domain.repositories.MainRepository
import okhttp3.RequestBody

class UserLoginUseCase(
    private val repository: MainRepository,
) {
    operator fun invoke(loginRequest: LoginRequest) =
        repository.userLogin(loginRequest)

}