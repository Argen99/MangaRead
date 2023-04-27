package com.geektech.mangaread.di

import com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_in.SignInViewModel
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_up.SignUpViewModel
import com.geektech.mangaread.presentation.ui.fragments.main_flow.main.MainViewModel
import com.geektech.mangaread.presentation.ui.fragments.main_flow.manga_detail.MangaDetailViewModel
import com.geektech.mangaread.presentation.ui.fragments.main_flow.manga_detail.comments.MangaCommentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<SignUpViewModel> {
        SignUpViewModel(
            registerUserUseCase = get(),
            userLoginUseCase = get()
        )
    }

    viewModel<SignInViewModel> {
        SignInViewModel(
            userLoginUseCase = get()
        )
    }

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
}