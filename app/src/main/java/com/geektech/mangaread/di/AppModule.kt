package com.geektech.mangaread.di

import com.geektech.mangaread.presentation.ui.fragments.auth_flow.AuthViewModel
import com.geektech.mangaread.presentation.ui.fragments.main_flow.main.MainViewModel
import com.geektech.mangaread.presentation.ui.fragments.main_flow.manga_detail.MangaDetailViewModel
import com.geektech.mangaread.presentation.ui.fragments.main_flow.manga_detail.comments.MangaCommentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MangaDetailViewModel> {
        MangaDetailViewModel(
            getMangaByIdUseCase = get(),
            getMangaComments = get(),
            addCommentUseCase = get(),
            getGenresUseCase = get()
        )
    }

    viewModel<MangaCommentsViewModel> {
        MangaCommentsViewModel(
            getMangaComments = get(),
            addCommentUseCase = get()
        )
    }

    viewModel<MainViewModel> {
        MainViewModel(
            getGenresUseCase = get(),
            getAllMangaUseCase = get(),
            getTopMangaUseCase = get()
        )
    }

    viewModel<AuthViewModel> {
        AuthViewModel(
            userLoginUseCase = get(),
            registerUserUseCase = get()
        )
    }
}