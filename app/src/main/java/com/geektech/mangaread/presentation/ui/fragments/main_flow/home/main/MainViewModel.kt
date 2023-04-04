package com.geektech.mangaread.presentation.ui.fragments.main_flow.home.main

import com.geektech.domain.model.Genres
import com.geektech.domain.use_cases.main.GetGenresUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val getGenresUseCase: GetGenresUseCase
) : BaseViewModel() {

    private val _getGenresState = MutableStateFlow<UIState<List<Genres>>>(UIState.Empty())
    val getGenresState = _getGenresState.asStateFlow()

    fun getGenres() {
        getGenresUseCase.invoke().collectFlow(_getGenresState)
    }
}