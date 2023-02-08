package com.geektech.mangaread.presentation.ui.fragments.main.home.main.all_manga

import com.geektech.domain.use_cases.main.GetAllMangaUseCase
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


//    fun getMangaPg(
//        type: String? = null, genreTitle: String? = null, enName: String? = null,
//        ruName: String? = null, search: String? = null
//    ) = getAllMangaUseCase(
//        type = type, genreTitle = genreTitle, enName = enName,
//        ruName = ruName, search = search
//    ).pagingRequest { it }

//    val mangaFlow: Flow<PagingData<MangaResult>>
//    private val SearchBy = MutableLiveData("")
//
//    init {
//        mangaFlow = SearchBy.asFlow()
//            .debounce(500)
//            .flatMapConcat {
//                getMangaUseCase.invoke(null, null,
//                    null, null, it, null)
//            }.cachedIn(viewModelScope)
//    }

}