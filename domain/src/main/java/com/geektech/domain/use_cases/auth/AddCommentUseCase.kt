package com.geektech.domain.use_cases.auth

import com.geektech.domain.repositories.AuthRepository

class AddCommentUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(id: Int, comment: String) =
        repository.addComment(id = id, comment = comment)
}