package com.geektech.mangaread.presentation.ui.fragments.main.home.main.all_manga

import com.geektech.domain.model.MangaResponse
import com.geektech.domain.model.MangaResult
import com.geektech.domain.use_cases.GetAllMangaUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.*

class AllMangaViewModel(
    private val getAllMangaUseCase: GetAllMangaUseCase
) : BaseViewModel() {

    private val _getMangaState = MutableStateFlow<UIState<MangaResponse>>(UIState.Empty())
    val getMangaState = _getMangaState.asStateFlow()

    fun getManga (
        type: String? = null, genreTitle: String? = null, enName: String? = null,
        ruName: String? = null, search: String? = null, page: Int? = null
    ) {
        getAllMangaUseCase(
            type = type, genreTitle = genreTitle, enName = enName,
            ruName = ruName, search = search, page = page
        ).collectFlow(_getMangaState)
    }

//    fun getMangaPg(
//        type: String? = null, genreTitle: String? = null, enName: String? = null,
//        ruName: String? = null, search: String? = null
//    ) = getAllMangaUseCase(
//        type = type, genreTitle = genreTitle, enName = enName,
//        ruName = ruName, search = search
//    ).pagingRequest { it }



//    val mangaFlow: Flow<PagingData<MangaResult>>
//    private val searchBy = MutableLiveData("")
//
//    init {
//        mangaFlow = searchBy.asFlow()
//            .debounce(500)
//            .flatMapConcat {
//                getMangaUseCase.invoke(null, null,
//                    null, null, it, null)
//            }.cachedIn(viewModelScope)
//    }

}