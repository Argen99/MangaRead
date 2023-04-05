package com.geektech.mangaread.presentation.ui.fragments.main_flow.manga_detail.comments

import com.geektech.domain.model.AddCommentResponse
import com.geektech.domain.model.MangaComments
import com.geektech.domain.use_cases.comment.AddCommentUseCase
import com.geektech.domain.use_cases.comment.GetMangaCommentsUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MangaCommentsViewModel(
    private val getMangaComments: GetMangaCommentsUseCase,
    private val addCommentUseCase: AddCommentUseCase
) : BaseViewModel() {

    private val _getCommentsState = MutableStateFlow<UIState<List<MangaComments>>>(UIState.Empty())
    val getCommentsState = _getCommentsState.asStateFlow()

    private val _addCommentState = MutableStateFlow<UIState<AddCommentResponse>>(UIState.Empty())
    val addCommentState = _addCommentState.asStateFlow()

    fun getCommentsById(id: Int, limit: Int? = null, offset: Int? = null) {
        getMangaComments(
            id = id, limit = limit, offset = offset).collectFlow(_getCommentsState)
    }

    fun addComment(id: Int, comment: String) {
        addCommentUseCase.invoke(id = id, comment = comment).collectFlow(_addCommentState)
    }
}
