package com.geektech.mangaread.presentation.ui.fragments.main_flow.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.geektech.domain.model.Genres
import com.geektech.domain.model.MangaResult
import com.geektech.domain.use_cases.main.GetGenresUseCase
import com.geektech.domain.use_cases.manga.GetAllMangaUseCase
import com.geektech.domain.use_cases.manga.GetTopMangaUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import com.geektech.mangaread.presentation.model.FilterArguments
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class MainViewModel(
    private val getGenresUseCase: GetGenresUseCase,
    private val getAllMangaUseCase: GetAllMangaUseCase,
    private val getTopMangaUseCase: GetTopMangaUseCase
) : BaseViewModel() {

    val allMangaFlow: Flow<PagingData<MangaResult>>

    private val searchBy = MutableStateFlow("")
    private val filter = MutableSharedFlow<FilterArguments>(replay = 1)

    private val _getGenresState = MutableStateFlow<UIState<List<Genres>>>(UIState.Empty())
    val getGenresState = _getGenresState.asStateFlow()

    private val _getTopMangaState = MutableStateFlow<UIState<List<MangaResult>>>(UIState.Empty())
    val getTopMangaState = _getTopMangaState.asStateFlow()

    fun getGenres() {
        getGenresUseCase.invoke().collectFlow(_getGenresState)
    }

    fun getTopManga(
        type: List<String>? = null, genreTitle: List<String>? = null, search: String? = null
    ) {
        getTopMangaUseCase(
            type = type, genreTitle = genreTitle, search = search
        ).collectFlow(_getTopMangaState)
    }

    init {
        filter.tryEmit(FilterArguments(emptyList(), emptyList()))
        allMangaFlow = combine(filter, searchBy) { filter, search ->
            Pair(filter, search)
        }.flatMapLatest { (filter, search) ->
            getAllMangaUseCase.invoke(
                search = search,
                type = filter.type,
                genreTitle = filter.genreTitle
            )
                .debounce(500)
                .cachedIn(viewModelScope)
        }
    }

    fun allMangaFilterBy(type: List<String>?, genreTitle: List<String>?) {
        filter.tryEmit(
            FilterArguments(type, genreTitle)
        )
    }

    fun allMangaSearchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}