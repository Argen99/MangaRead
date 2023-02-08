package com.geektech.data.local_db.room

import androidx.room.*
import com.geektech.data.local_db.room.model.CurrentUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentUserDao {

    @Update(entity = CurrentUserEntity::class)
    suspend fun updateUsername(updateUsernameTuple: CurrentUserUpdateUsernameTuple)

    @Insert
    suspend fun saveUser(user: CurrentUserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUser(userId: Int): CurrentUserEntity?

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUser(userId: Int)
}