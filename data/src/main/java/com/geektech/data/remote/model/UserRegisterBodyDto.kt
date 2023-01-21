package com.geektech.data.remote.model


data class UserRegisterBodyDto(
    val username: String,
    val nickname: String,
    val image_file: String,
    val password: String,
)

