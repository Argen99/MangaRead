package com.geektech.domain.model

data class LoginResponse(
    val status: Int,
    val user: String,
    val access: String,
    val refresh: String
)