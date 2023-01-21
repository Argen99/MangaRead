package com.geektech.domain.model

data class MangaResult(
    val id: Int,
    val en_name: String,
    val ru_name: String,
    val dir: String? = null,
    val image: String,
    val description: String? = null,
    val chapters_quantity: Int? = null,
    val issue_year: Int? = null,
    val created_at: String,
    val updated_at: String? = null,
    val type: String,
    val likes: Int,
    val rating: Double,
    val genre: ArrayList<Int>
)