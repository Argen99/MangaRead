package com.geektech.mangaread.presentation.ui.fragments.main.home.main.top_manga

import com.geektech.domain.model.MangaResponse
import com.geektech.domain.use_cases.GetTopMangaUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TopMangaViewModel(
    private val getTopMangaUseCase: GetTopMangaUseCase
) : BaseViewModel() {

    private val _getTopMangaState = MutableStateFlow<UIState<MangaResponse>>(UIState.Empty())
    val getTopMangaState = _getTopMangaState.asStateFlow()

    fun getTopManga (
        type: String? = null, genreTitle: String? = null, enName: String? = null,
        ruName: String? = null, search: String? = null, page: Int? = null
    ) {
        getTopMangaUseCase(
            type = type, genreTitle = genreTitle, enName = enName,
            ruName = ruName, search = search, page = page
        ).collectFlow(_getTopMangaState)
    }
}