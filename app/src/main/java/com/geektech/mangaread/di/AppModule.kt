package com.geektech.mangaread.di

import com.geektech.mangaread.presentation.ui.fragments.auth.sign_in.SignInViewModel
import com.geektech.mangaread.presentation.ui.fragments.auth.sign_up.SignUpViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.home.main.MainViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.home.main.all_manga.AllMangaViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.home.main.top_manga.TopMangaViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.home.manga_detail.MangaDetailViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.home.manga_detail.comments.MangaCommentsViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<SignUpViewModel> {
        SignUpViewModel(
            registerUserUseCase = get(),
            userLoginUseCase = get(),
            roomRepository = get()
        )
    }

    viewModel<SignInViewModel> {
        SignInViewModel(
            userLoginUseCase = get()
        )
    }

    viewModel<AllMangaViewModel> {
        AllMangaViewModel(getAllMangaUseCase = get())
    }

    viewModel<MangaDetailViewModel> {
        MangaDetailViewModel(
            getMangaByIdUseCase = get(),
            getMangaComments = get(),
            addCommentUseCase = get(),
            getGenresUseCase = get()
        )
    }

    viewModel<TopMangaViewModel> {
        TopMangaViewModel(getTopMangaUseCase = get())
    }

    viewModel<MangaCommentsViewModel> {
        MangaCommentsViewModel(
            getMangaComments = get(),
            addCommentUseCase = get()
        )
    }

    viewModel<ProfileViewModel> {
        ProfileViewModel(
            logoutUseCase = get(),
            roomRepository = get()
        )
    }
    viewModel<MainViewModel> {
        MainViewModel(
            getGenresUseCase = get()
        )
    }
}