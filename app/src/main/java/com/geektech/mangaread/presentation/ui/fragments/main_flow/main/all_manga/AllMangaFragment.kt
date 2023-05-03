package com.geektech.mangaread.presentation.ui.fragments.main_flow.main.all_manga

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.databinding.FragmentAllMangaBinding
import com.geektech.mangaread.presentation.ui.adapters.MangaPagingAdapter
import com.geektech.mangaread.presentation.ui.fragments.main_flow.main.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AllMangaFragment(private val openMangaDetails: (id: String) -> Unit) :
    BaseFragment<FragmentAllMangaBinding, MainViewModel>(R.layout.fragment_all_manga) {

    override val binding by viewBinding(FragmentAllMangaBinding::bind)
    override val viewModel by activityViewModel<MainViewModel>()

    private val mangaAdapter: MangaPagingAdapter by lazy {
        MangaPagingAdapter(this::onItemClick)
    }

    override fun initialize() {
        binding.rvAllManga.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mangaAdapter
        }
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allMangaFlow.collectLatest {
                mangaAdapter.submitData(it)
            }
        }
    }

    private fun onItemClick(id: String) {
        openMangaDetails(id)
    }
}