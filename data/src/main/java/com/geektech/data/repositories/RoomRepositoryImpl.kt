package com.geektech.data.repositories

import com.geektech.data.core.utils.Constants
import com.geektech.data.local_db.room.CurrentUserDao
import com.geektech.data.local_db.room.model.CurrentUserEntity
import com.geektech.domain.model.CurrentUser
import com.geektech.domain.repositories.RoomRepository

class RoomRepositoryImpl(
    private val userDao: CurrentUserDao
) : RoomRepository {

    override suspend fun getUser(): CurrentUser? {
        return userDao.getUser(Constants.USER_ID)?.toCurrentUser()
    }

    override suspend fun saveUser(user: CurrentUser) {
        val entity = CurrentUserEntity.toCurrentUserEntity(
            image = user.image,
            username = user.username,
            fullname = user.fullname,
            password = user.password
        )
        userDao.saveUser(entity)
    }

    override suspend fun deleteUser() {
        userDao.deleteUser(Constants.USER_ID)
    }
}