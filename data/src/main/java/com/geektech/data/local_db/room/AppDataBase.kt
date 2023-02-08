package com.geektech.data.local_db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektech.data.local_db.room.model.CurrentUserEntity

@Database(
    version = 1,
    entities = [
        CurrentUserEntity::class
    ]
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getUserDao(): CurrentUserDao
}