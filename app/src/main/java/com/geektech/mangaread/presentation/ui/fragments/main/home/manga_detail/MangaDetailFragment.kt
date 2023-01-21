package com.geektech.mangaread.presentation.ui.fragments.main.home.manga_detail

import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.MangaResult
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.loadImage
import com.geektech.mangaread.core.utils.Constants
import com.geektech.mangaread.databinding.FragmentMangaDetailBinding
import com.geektech.mangaread.presentation.ui.adapters.CommentsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaDetailFragment : BaseFragment<FragmentMangaDetailBinding,
        MangaDetailViewModel>(R.layout.fragment_manga_detail) {

    override val binding by viewBinding(FragmentMangaDetailBinding::bind)
    override val viewModel by viewModel<MangaDetailViewModel>()

    private var commentsList = arrayListOf<String>()
    private val commentsAdapter: CommentsAdapter by lazy {
        CommentsAdapter(commentsList, this::onItemClick)
    }

    override fun initialize() {
        binding.rvComments.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = commentsAdapter
        }
    }

    override fun checkArguments() {
        val id = arguments?.getString(Constants.MANGA_ID_KEY)
        if (id != null) {
            viewModel.getMangaById(id)
        }
    }

    override fun setupObservers() {
        viewModel.getMangaByIdState.collectState(
            onLoading = {

            },
            onSuccess = {
                setDetails(it)
            },

            onError = {

            }
        )
    }

    override fun setupClickListeners() {
        binding.tvComments.setOnClickListener {
            navigate(R.id.mangaCommentsFragment)
        }
        binding.tvReadMore.setOnClickListener {
            binding.tvSynopsis.maxLines = Integer.MAX_VALUE
        }
    }

    private fun setDetails(model: MangaResult) {
        binding.ivMangaImageDt.loadImage(model.image)
        binding.tvMangaTypeDt.text = model.type
        binding.tvMangaGenreDt.text = model.genre.toString()
        binding.tvIssueYearDt.text = model.issue_year.toString()
        binding.tvSynopsis.text = model.description

    }

    private fun onItemClick(position: Int) {

    }

}