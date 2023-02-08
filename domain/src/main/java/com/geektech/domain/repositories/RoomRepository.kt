package com.geektech.domain.repositories

import com.geektech.domain.model.CurrentUser

interface RoomRepository {

    suspend fun getUser(): CurrentUser?

    suspend fun saveUser(user: CurrentUser)

    suspend fun deleteUser()
}