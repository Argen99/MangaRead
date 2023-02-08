package com.geektech.domain.use_cases.main

import com.geektech.domain.repositories.MainRepository
import okhttp3.RequestBody

class UserRegisterUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(
        username: RequestBody,
        nickname: RequestBody,
        imageFile: RequestBody,
        password: RequestBody,
        imageUri: String
    ) = repository.userRegister(
        username = username, nickname = nickname,
        imageFile = imageFile, password = password,
        imageUri = imageUri)
}