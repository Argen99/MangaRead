package com.geektech.data.remote.model

data class MangaResponseDto(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<MangaResultDto>
)