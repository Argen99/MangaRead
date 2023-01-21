package com.geektech.mangaread.di

import com.geektech.mangaread.presentation.ui.fragments.auth.sign_up.SignUpViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.home.main.all_manga.AllMangaViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.home.main.top_manga.TopMangaViewModel
import com.geektech.mangaread.presentation.ui.fragments.main.home.manga_detail.MangaDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<AllMangaViewModel> {
        AllMangaViewModel(getAllMangaUseCase = get())
    }

    viewModel<MangaDetailViewModel> {
        MangaDetailViewModel(getMangaByIdUseCase = get())
    }

    viewModel<TopMangaViewModel> {
        TopMangaViewModel(getTopMangaUseCase = get())
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(registerUserUseCase = get())
    }

}