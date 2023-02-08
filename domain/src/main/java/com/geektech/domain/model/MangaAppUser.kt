package com.geektech.domain.model

data class MangaAppUser(
    val id: Int,
    val username: String,
    val nickname: String,
    val image: String? = null,
    val image_file: String,
)