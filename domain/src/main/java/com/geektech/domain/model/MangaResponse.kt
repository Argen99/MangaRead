package com.geektech.domain.model

data class MangaResponse(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<MangaResult>
)