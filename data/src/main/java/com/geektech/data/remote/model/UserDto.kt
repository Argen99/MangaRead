package com.geektech.data.remote.model

import com.geektech.domain.model.User

data class UserDto (
    val status: Int,
    val username: String,
    val nickname: String,
    val message: String
)