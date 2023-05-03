package com.geektech.mangaread.presentation.model

data class FilterArguments(
    val type: List<String>,
    val genreTitle: List<String>
)

data class TopMangaFilterArguments(
    val search: String? = null,
    val type: List<String>? = null,
    val genreTitle: List<String>? = null
)
