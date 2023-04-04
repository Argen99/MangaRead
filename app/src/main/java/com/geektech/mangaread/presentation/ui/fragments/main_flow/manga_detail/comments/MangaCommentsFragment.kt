package com.geektech.mangaread.presentation.ui.fragments.main_flow.manga_detail.comments

import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.MangaComments
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.gone
import com.geektech.mangaread.core.extensions.showToast
import com.geektech.mangaread.core.extensions.visible
import com.geektech.mangaread.core.utils.Constants
import com.geektech.mangaread.databinding.FragmentMangaCommentsBinding
import com.geektech.mangaread.presentation.ui.adapters.CommentsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaCommentsFragment : BaseFragment<FragmentMangaCommentsBinding,
        MangaCommentsViewModel>(R.layout.fragment_manga_comments) {

    override val binding by viewBinding(FragmentMangaCommentsBinding::bind)
    override val viewModel by viewModel<MangaCommentsViewModel>()

    private var mangaId: Int? = null

    private var commentsList = mutableListOf<MangaComments>()
    private val commentsAdapter: CommentsAdapter by lazy {
        CommentsAdapter(commentsList, this::onItemClick, true)
    }

    override fun checkArguments() {
        val id = arguments?.getString(Constants.ID_MDF_MCF)
        if (id != null) {
            mangaId = id.toInt()
            viewModel.getCommentsById(id.toInt())
        }
    }

    override fun initialize() {
        binding.rvAllComments.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = commentsAdapter
        }
    }

    override fun setupClickListeners() {
        binding.btnAddComment.setOnClickListener {
            addComment()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addComment() {
        if (binding.etAddComment.text.isEmpty()) {
            binding.etAddComment.visible()
            binding.etAddComment.requestFocus()
            val im: InputMethodManager =
                requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            im.showSoftInput(binding.etAddComment, InputMethodManager.SHOW_FORCED)
        } else {
            binding.etAddComment.gone()
            mangaId?.let {
                viewModel.addComment(
                    it, binding.etAddComment.text.toString()
                )
                binding.etAddComment.text = null
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupObservers() {
        viewModel.getCommentsState.collectState(
            onLoading = {
                binding.progressBar.visible()
            },
            onSuccess = {
                commentsAdapter.submitList(it.toMutableList())
                binding.progressBar.gone()
            },
            onError = {
                context?.showToast(it)
            }
        )

        viewModel.addCommentState.collectState(
            onLoading = {
                binding.progressBar.visible()
            },
            onSuccess = {
                binding.progressBar.gone()
                mangaId?.let {viewModel.getCommentsById(it)}
                commentsAdapter.notifyDataSetChanged()
            },
            onError = {
                context?.showToast(it)
            }
        )
    }

    private fun onItemClick(position: Int) {

    }
}