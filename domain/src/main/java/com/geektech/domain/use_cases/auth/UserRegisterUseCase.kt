package com.geektech.domain.use_cases.auth

import com.geektech.domain.repositories.AuthRepository
import okhttp3.RequestBody

class UserRegisterUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(
        username: RequestBody,
        nickname: RequestBody,
        imageFile: RequestBody,
        password: RequestBody
    ) = repository.userRegister(
        username = username, nickname = nickname,
        imageFile = imageFile, password = password
    )
}