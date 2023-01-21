package com.geektech.mangaread.presentation.ui.fragments.main.home.manga_detail

import com.geektech.domain.model.MangaResponse
import com.geektech.domain.model.MangaResult
import com.geektech.domain.use_cases.GetMangaByIdUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MangaDetailViewModel(
    private val getMangaByIdUseCase: GetMangaByIdUseCase
) : BaseViewModel() {

    private val _getMangaByIdState = MutableStateFlow<UIState<MangaResult>>(UIState.Empty())
    val getMangaByIdState = _getMangaByIdState.asStateFlow()


    fun getMangaById(id: String) {
        getMangaByIdUseCase(id).collectFlow(_getMangaByIdState)
    }

}