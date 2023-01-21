package com.geektech.mangaread.di

import com.geektech.domain.use_cases.GetMangaByIdUseCase
import com.geektech.domain.use_cases.GetAllMangaUseCase
import com.geektech.domain.use_cases.GetTopMangaUseCase
import com.geektech.domain.use_cases.RegisterUserUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetAllMangaUseCase> {
        GetAllMangaUseCase(repository = get())
    }

    factory<GetMangaByIdUseCase> {
        GetMangaByIdUseCase(repository = get())
    }

    factory<GetTopMangaUseCase> {
        GetTopMangaUseCase(repository = get())
    }

    factory<RegisterUserUseCase> {
        RegisterUserUseCase(repository = get())
    }
}