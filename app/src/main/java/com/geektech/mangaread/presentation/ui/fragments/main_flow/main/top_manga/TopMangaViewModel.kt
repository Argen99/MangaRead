package com.geektech.mangaread.presentation.ui.fragments.main_flow.main.top_manga

import com.geektech.domain.model.MangaResult
import com.geektech.domain.use_cases.main.GetTopMangaUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TopMangaViewModel(
    private val getTopMangaUseCase: GetTopMangaUseCase
) : BaseViewModel() {

    private val _getTopMangaState = MutableStateFlow<UIState<List<MangaResult>>>(UIState.Empty())
    val getTopMangaState = _getTopMangaState.asStateFlow()

    fun getTopManga (
        limit: Int? = null, offset: Int? = null, type: List<String>? = null, genreTitle: List<String>? = null,
        enName: String? = null, ruName: String? = null, search: String? = null
    ) {
        getTopMangaUseCase(
            limit = limit, offset = offset, type = type, genreTitle = genreTitle, enName = enName,
            ruName = ruName, search = search
        ).collectFlow(_getTopMangaState)
    }
}