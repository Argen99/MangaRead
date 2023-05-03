package com.geektech.domain.use_cases.comment

import com.geektech.domain.repositories.CommentsRepository

class GetMangaCommentsUseCase(
    private val repository: CommentsRepository
) {
    operator fun invoke(id: Int, limit: Int?, offset: Int?) =
        repository.getCommentsById(id = id, limit = limit, offset = offset)
}