package com.geektech.domain.model

data class MangaComments(
    val id: Int,
    val user: MangaAppUser,
    val text: String,
)