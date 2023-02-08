package com.geektech.data.remote.model

data class MangaCommentsDto(
    val id: Int,
    val user: MangaAppUserDto,
    val text: String,
)