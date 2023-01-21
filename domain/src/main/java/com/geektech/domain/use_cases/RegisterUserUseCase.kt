package com.geektech.domain.use_cases

import com.geektech.domain.model.UserRegisterBody
import com.geektech.domain.repositories.Repository

class RegisterUserUseCase(
    private val repository: Repository
) {
    operator fun invoke(body: UserRegisterBody) = repository.userRegister(body)
}