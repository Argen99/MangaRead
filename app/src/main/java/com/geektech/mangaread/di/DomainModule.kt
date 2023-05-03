package com.geektech.mangaread.di

import com.geektech.domain.repositories.AuthRepository
import com.geektech.domain.repositories.CommentsRepository
import com.geektech.domain.repositories.MainRepository
import com.geektech.domain.repositories.MangaRepository
import com.geektech.domain.use_cases.auth.LogoutUseCase
import com.geektech.domain.use_cases.auth.UserLoginUseCase
import com.geektech.domain.use_cases.auth.UserRegisterUseCase
import com.geektech.domain.use_cases.comment.AddCommentUseCase
import com.geektech.domain.use_cases.comment.GetMangaCommentsUseCase
import com.geektech.domain.use_cases.main.GetGenresUseCase
import com.geektech.domain.use_cases.manga.GetAllMangaUseCase
import com.geektech.domain.use_cases.manga.GetMangaByIdUseCase
import com.geektech.domain.use_cases.manga.GetTopMangaUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetAllMangaUseCase> {
        GetAllMangaUseCase(repository = get<MangaRepository>())
    }

    factory<GetMangaByIdUseCase> {
        GetMangaByIdUseCase(repository = get<MangaRepository>())
    }

    factory<GetTopMangaUseCase> {
        GetTopMangaUseCase(repository = get<MangaRepository>())
    }

    factory<UserRegisterUseCase> {
        UserRegisterUseCase(repository = get<AuthRepository>())
    }

    factory<GetMangaCommentsUseCase> {
        GetMangaCommentsUseCase(repository = get<CommentsRepository>())
    }

    factory<UserLoginUseCase> {
        UserLoginUseCase(repository = get<AuthRepository>())
    }
    factory<AddCommentUseCase> {
        AddCommentUseCase(repository = get<CommentsRepository>())
    }

    factory<LogoutUseCase> {
        LogoutUseCase(repository = get<AuthRepository>())
    }

    factory<GetGenresUseCase> {
        GetGenresUseCase(repository = get<MainRepository>())
    }
}