package com.geektech.data.remote.model

data class LoginResponseDto(
    val status: Int,
    val user: String,
    val access: String,
    val refresh: String
)