package com.geektech.domain.use_cases.comment

import com.geektech.domain.repositories.CommentsRepository

class AddCommentUseCase(
    private val repository: CommentsRepository
) {
    operator fun invoke(id: Int, comment: String) =
        repository.addComment(id = id, comment = comment)
}