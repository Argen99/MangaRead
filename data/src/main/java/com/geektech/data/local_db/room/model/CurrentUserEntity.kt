package com.geektech.data.local_db.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.geektech.data.core.utils.Constants
import com.geektech.domain.model.CurrentUser


@Entity(tableName = "users")
data class CurrentUserEntity (
    @PrimaryKey val id: Int,
    val image: String,
    val username: String,
    val fullname: String?,
    val password: String
    ) {

    fun toCurrentUser(): CurrentUser = CurrentUser(
        image = image,
        username = username,
        fullname = fullname,
        password = password
    )

    companion object {
        fun toCurrentUserEntity(
            image: String,
            username: String,
            fullname: String?,
            password: String
        ): CurrentUserEntity = CurrentUserEntity(
            id = Constants.USER_ID,
            image = image,
            username = username,
            fullname = fullname,
            password = password
        )
    }
}