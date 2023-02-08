package com.geektech.mangaread.di

import com.geektech.domain.repositories.AuthRepository
import com.geektech.domain.repositories.MainRepository
import com.geektech.domain.use_cases.auth.AddCommentUseCase
import com.geektech.domain.use_cases.auth.LogoutUseCase
import com.geektech.domain.use_cases.main.UserLoginUseCase
import com.geektech.domain.use_cases.main.UserRegisterUseCase
import com.geektech.domain.use_cases.main.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetAllMangaUseCase> {
        GetAllMangaUseCase(repository = get<MainRepository>())
    }

    factory<GetMangaByIdUseCase> {
        GetMangaByIdUseCase(repository = get<MainRepository>())
    }

    factory<GetTopMangaUseCase> {
        GetTopMangaUseCase(repository = get<MainRepository>())
    }

    factory<UserRegisterUseCase> {
        UserRegisterUseCase(repository = get<MainRepository>())
    }

    factory<GetMangaCommentsUseCase> {
        GetMangaCommentsUseCase(repository = get<MainRepository>())
    }

    factory<UserLoginUseCase> {
        UserLoginUseCase(repository = get<MainRepository>())
    }
    factory<AddCommentUseCase> {
        AddCommentUseCase(repository = get<AuthRepository>())
    }

    factory<LogoutUseCase> {
        LogoutUseCase(repository =  get<AuthRepository>())
    }

    factory<GetGenresUseCase> {
        GetGenresUseCase(repository =  get<MainRepository>())
    }
}