package com.geektech.data.local_db.room

data class CurrentUserSignInTuple(
    val id: Long,
    val username: String
)

data class CurrentUserUpdateUsernameTuple(
    val id: Long,
    val username: String
)