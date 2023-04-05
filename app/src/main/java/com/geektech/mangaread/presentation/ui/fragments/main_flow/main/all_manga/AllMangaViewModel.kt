package com.geektech.mangaread.presentation.ui.fragments.main_flow.main.all_manga

import com.geektech.domain.use_cases.manga.GetAllMangaUseCase
import com.geektech.mangaread.core.base.BaseViewModel

class AllMangaViewModel(
    private val getAllMangaUseCase: GetAllMangaUseCase
) : BaseViewModel() {

    fun getManga (
        type: List<String>? = null,
        genreTitle: List<String>? = null,
        enName: String? = null,
        ruName: String? = null,
        search: String? = null
    ) =
        getAllMangaUseCase(
            type = type, genreTitle = genreTitle, enName = enName,
            ruName = ruName, search = search
        ).pagingRequest { it }
}