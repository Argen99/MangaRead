package com.geektech.mangaread.di

import com.geektech.data.local_db.prefs.SelectedItemsPrefs
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.data.remote.api_service.AuthApiService
import com.geektech.data.remote.api_service.CommentsApiService
import com.geektech.data.remote.api_service.MainApiService
import com.geektech.data.remote.api_service.MangaApiService
import com.geektech.data.repositories.AuthRepositoryImpl
import com.geektech.data.repositories.CommentsRepositoryImpl
import com.geektech.data.repositories.MainRepositoryImpl
import com.geektech.data.repositories.MangaRepositoryImpl
import com.geektech.domain.repositories.AuthRepository
import com.geektech.domain.repositories.CommentsRepository
import com.geektech.domain.repositories.MainRepository
import com.geektech.domain.repositories.MangaRepository
import org.koin.dsl.module

val dataModule = module {

    single<MainRepository> {
        MainRepositoryImpl(
            apiService = get<MainApiService>()
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(apiService = get<AuthApiService>())
    }

    single<MangaRepository> {
        MangaRepositoryImpl(apiService = get<MangaApiService>())
    }

    single<CommentsRepository> {
        CommentsRepositoryImpl(apiService = get<CommentsApiService>())
    }

    single<TokenManager> {
        TokenManager(context = get())
    }

    single<SelectedItemsPrefs> {
        SelectedItemsPrefs(context = get())
    }
}