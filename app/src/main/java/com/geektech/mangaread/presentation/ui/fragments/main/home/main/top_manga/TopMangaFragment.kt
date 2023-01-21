package com.geektech.mangaread.presentation.ui.fragments.main.home.main.top_manga

import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.MangaResult
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.databinding.FragmentTopMangaBinding
import com.geektech.mangaread.presentation.ui.adapters.MangaAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopMangaFragment : BaseFragment<FragmentTopMangaBinding,
        TopMangaViewModel>(R.layout.fragment_top_manga) {

    override val binding by viewBinding(FragmentTopMangaBinding::bind)
    override val viewModel by viewModel<TopMangaViewModel>()


    private val mangaList = arrayListOf<MangaResult>()
    private val mangaAdapter: MangaAdapter by lazy {
        MangaAdapter(mangaList, this::onItemClick)
    }

    override fun setupRequest() {
        viewModel.getTopManga()
    }

    override fun initialize() {
        binding.rvTopManga.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mangaAdapter
        }
    }

    override fun setupObservers() {
        viewModel.getTopMangaState.collectState(
            onLoading = {},
            onError = {},
            onSuccess = {
                mangaAdapter.submitList(it.results)
            }
        )
    }

    private fun onItemClick(id: String) {
    }

}