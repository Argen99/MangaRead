package com.geektech.mangaread.presentation.ui.fragments.main_flow.home.manga_detail

import android.annotation.SuppressLint
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.Genres
import com.geektech.domain.model.MangaComments
import com.geektech.domain.model.MangaResult
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.gone
import com.geektech.mangaread.core.extensions.loadImage
import com.geektech.mangaread.core.extensions.showToast
import com.geektech.mangaread.core.extensions.visible
import com.geektech.mangaread.core.utils.Constants
import com.geektech.mangaread.databinding.FragmentMangaDetailBinding
import com.geektech.mangaread.presentation.ui.adapters.CommentsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaDetailFragment() : BaseFragment<FragmentMangaDetailBinding,
        MangaDetailViewModel>(R.layout.fragment_manga_detail) {

    override val binding by viewBinding(FragmentMangaDetailBinding::bind)
    override val viewModel by viewModel<MangaDetailViewModel>()

    private var commentsList = arrayListOf<MangaComments>()
    private val commentsAdapter: CommentsAdapter by lazy {
        CommentsAdapter(commentsList, this::onItemClick)
    }
    private var mangaId: String? = null

    private var listOfAllGenres = arrayListOf<Genres>()

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
            mangaId = id
            viewModel.getMangaById(id)
            viewModel.getCommentsById(id.toInt())
        }
    }

    override fun setupRequest() {
        viewModel.getGenres()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupObservers() {
        viewModel.getMangaByIdState.collectState(
            onLoading = { binding.progressBarMdf.visible() },
            onSuccess = {
                binding.progressBarMdf.gone()
                setDetails(it) },
            onError = { context?.showToast(it) }
        )

        viewModel.getCommentsState.collectState(
            onLoading = {},
            onSuccess = {
                commentsAdapter.submitList(it)
                checkData(it) },
            onError = { context?.showToast(it) }
        )

        viewModel.addCommentState.collectState(
            onLoading = {},
            onSuccess = {
                mangaId?.let {viewModel.getCommentsById(it.toInt())}
                commentsAdapter.notifyDataSetChanged() },
            onError = { context?.showToast(it) }
        )

        viewModel.getGenresState.collectState(
            onLoading = {},
            onSuccess = { listOfAllGenres = it as ArrayList<Genres> },
            onError = { context?.showToast(it) }
        )
    }

    override fun setupClickListeners() {
        binding.tvReadMore.setOnClickListener {
            readMore()
        }

        binding.tvAddComment.setOnClickListener {
            addComment()
        }

        binding.btnBack.setOnClickListener {
           navigateUp()
        }
    }

    private fun addComment() {
        if (binding.etAddComment.text.isEmpty()) {
            binding.etAddComment.visible()
            binding.etAddComment.requestFocus()
            val im: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.showSoftInput(binding.etAddComment, InputMethodManager.SHOW_FORCED)
        } else {
            binding.etAddComment.gone()
            mangaId?.let {
                viewModel.addComment(
                    it.toInt(), binding.etAddComment.text.toString()
                )
                binding.etAddComment.text = null
            }
        }
    }

    private fun readMore() {
        if (binding.tvSynopsis.maxLines == 7) {
            binding.tvSynopsis.maxLines = Integer.MAX_VALUE
            binding.gradientBg.gone()
            binding.tvReadMore.text = getText(R.string.hide)
        } else {
            binding.tvSynopsis.maxLines = 7
            binding.gradientBg.visible()
            binding.tvReadMore.text = getText(R.string.read_more)
        }
    }

    private fun onItemClick(position: Int) {
        navigate(
            R.id.action_mangaDetailFragment_to_mangaCommentsFragment,
            bundleOf(Constants.ID_MDF_MCF to mangaId)
        )
    }

    private fun setDetails(model: MangaResult) {
        binding.ivMangaImageDt.loadImage(model.image)
        binding.tvMangaTypeDt.text = model.type
        binding.tvIssueYearDt.text = model.issue_year.toString()
        binding.tvSynopsis.text = model.description
        binding.tvMangaName.text = model.ru_name
        val genresOfTheCurrentManga = arrayListOf<String>()

        for (genreId in model.genre) {
            for (genre in listOfAllGenres) {
                if (genreId == genre.id) {
                    genresOfTheCurrentManga.add(genre.title)
                }
            }
        }
        binding.tvMangaGenreDt.text = genresOfTheCurrentManga.joinToString()
    }

    private fun checkData(it: List<MangaComments>) {
        if (it.isEmpty()) {
            binding.rvComments.gone()
            binding.tvNoReviews.visible()
        } else {
            binding.rvComments.visible()
            binding.tvNoReviews.gone()
        }
    }
}