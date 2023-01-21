package com.geektech.mangaread.di

import com.geektech.data.remote.RetrofitClient
import com.geektech.data.remote.api_service.MangaReadApiService
import com.geektech.data.repositories.RepositoryImpl
import com.geektech.domain.repositories.Repository
import org.koin.dsl.module

val dataModule = module {

    single<Repository> {
        RepositoryImpl(apiService = get())
    }

    single<MangaReadApiService> {
        RetrofitClient.create()
    }

}