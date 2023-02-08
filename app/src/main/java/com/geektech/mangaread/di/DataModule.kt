package com.geektech.mangaread.di

import androidx.room.Room
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.data.local_db.room.AppDataBase
import com.geektech.data.local_db.room.CurrentUserDao
import com.geektech.data.remote.api_service.AuthApiService
import com.geektech.data.remote.api_service.MangaReadApiService
import com.geektech.data.repositories.AuthRepositoryImpl
import com.geektech.data.repositories.MainRepositoryImpl
import com.geektech.data.repositories.RoomRepositoryImpl
import com.geektech.domain.repositories.AuthRepository
import com.geektech.domain.repositories.MainRepository
import com.geektech.domain.repositories.RoomRepository
import org.koin.dsl.module

val dataModule = module {

    single<MainRepository> {
        MainRepositoryImpl(
            apiService = get<MangaReadApiService>())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(apiService = get<AuthApiService>())
    }

    single<RoomRepository> {
        RoomRepositoryImpl(userDao = get<CurrentUserDao>())
    }

    single <TokenManager>{
        TokenManager(context = get())
    }

    single<AppDataBase> {
        Room.databaseBuilder(get(), AppDataBase::class.java, "user_db").build()
    }

    single<CurrentUserDao> { provideUserDao(get()) }
}

fun provideUserDao(appDataBase: AppDataBase): CurrentUserDao {
    return appDataBase.getUserDao()
}