package com.geektech.mangaread.presentation.ui.fragments.main_flow.home.manga_detail

import com.geektech.domain.model.AddCommentResponse
import com.geektech.domain.model.Genres
import com.geektech.domain.model.MangaComments
import com.geektech.domain.model.MangaResult
import com.geektech.domain.use_cases.auth.AddCommentUseCase
import com.geektech.domain.use_cases.main.GetGenresUseCase
import com.geektech.domain.use_cases.main.GetMangaCommentsUseCase
import com.geektech.domain.use_cases.main.GetMangaByIdUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MangaDetailViewModel(
    private val getMangaByIdUseCase: GetMangaByIdUseCase,
    private val getMangaComments: GetMangaCommentsUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : BaseViewModel() {

    private val _getMangaByIdState = MutableStateFlow<UIState<MangaResult>>(UIState.Empty())
    val getMangaByIdState = _getMangaByIdState.asStateFlow()

    private val _getCommentsState = MutableStateFlow<UIState<List<MangaComments>>>(UIState.Empty())
    val getCommentsState = _getCommentsState.asStateFlow()

    private val _addCommentState = MutableStateFlow<UIState<AddCommentResponse>>(UIState.Empty())
    val addCommentState = _addCommentState.asStateFlow()

    private val _getGenresState = MutableStateFlow<UIState<List<Genres>>>(UIState.Empty())
    val getGenresState = _getGenresState.asStateFlow()

    fun getMangaById(id: String) {
        getMangaByIdUseCase(id).collectFlow(_getMangaByIdState)
    }

    fun getCommentsById(id: Int, limit: Int? = null, offset: Int? = null) {
        getMangaComments(
            id = id, limit = limit, offset = offset).collectFlow(_getCommentsState)
    }

    fun addComment(id: Int, comment: String) {
        addCommentUseCase.invoke(id = id, comment = comment).collectFlow(_addCommentState)
    }

    fun getGenres(){
        getGenresUseCase.invoke().collectFlow(_getGenresState)
    }

}