package com.geektech.domain.model


data class UserRegisterBody(
    val username: String,
    val nickname: String,
    val image_file: String,
    val password: String,
)

