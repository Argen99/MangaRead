package com.geektech.data.remote.model

data class MangaAppUserDto(
    val id: Int,
    val username: String,
    val nickname: String,
    val image: String? = null,
    val image_file: String,
)
