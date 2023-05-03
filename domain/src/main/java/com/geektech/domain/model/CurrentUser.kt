package com.geektech.domain.model

data class CurrentUser(
    val image: String,
    val username: String,
    val fullname: String?,
    val password: String
)