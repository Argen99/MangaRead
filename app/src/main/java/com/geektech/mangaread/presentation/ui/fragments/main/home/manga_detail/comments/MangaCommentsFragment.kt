package com.geektech.mangaread.presentation.ui.fragments.main.home.manga_detail.comments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.FragmentMangaCommentsBinding
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.presentation.ui.adapters.CommentsAdapter

class MangaCommentsFragment : BaseFragment<FragmentMangaCommentsBinding,
        MangaCommentsViewModel>(R.layout.fragment_manga_comments) {

    override val binding by viewBinding(FragmentMangaCommentsBinding::bind)
    override val viewModel by viewModels<MangaCommentsViewModel>()

    private var commentsList = arrayListOf<String>()
    private val commentsAdapter: CommentsAdapter by lazy {
        CommentsAdapter(commentsList, this::onItemClick, true)
    }

    override fun initialize() {
        binding.rvAllComments.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
            adapter = commentsAdapter
        }
    }

    private fun onItemClick(position: Int) {

    }
}