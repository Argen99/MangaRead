package com.geektech.mangaread.presentation.ui.fragments.main_flow.main.top_manga

import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.MangaResult
import com.geektech.domain.model.SortByIssueYear
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.gone
import com.geektech.mangaread.core.extensions.showToast
import com.geektech.mangaread.core.extensions.visible
import com.geektech.mangaread.databinding.FragmentTopMangaBinding
import com.geektech.mangaread.presentation.ui.adapters.MangaAdapter
import com.geektech.mangaread.presentation.ui.fragments.main_flow.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class TopMangaFragment(
    private val openMangaDetails: (id: String) -> Unit
) : BaseFragment<FragmentTopMangaBinding,
        MainViewModel>(R.layout.fragment_top_manga) {

    override val binding by viewBinding(FragmentTopMangaBinding::bind)
    override val viewModel by activityViewModel<MainViewModel>()

    private val mangaList = listOf<MangaResult>()
    private val mangaAdapter: MangaAdapter by lazy {
        MangaAdapter(mangaList, this::onItemClick)
    }
    private var sortByYear: SortByIssueYear? = null

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
            onLoading = { binding.progressBar.visible() },
            onError = { context?.showToast(it) },
            onSuccess = { manga ->
                onSuccessGetTopManga(manga)
            }
        )
    }

    private fun onSuccessGetTopManga(manga: List<MangaResult>) {
        val filteredMangas: ArrayList<MangaResult> = arrayListOf()
        if (sortByYear != null) {
            for (el in manga) {
                if (el.issue_year != null) {
                    if (sortByYear?.to != null && sortByYear?.from != null) {
                        if (el.issue_year!! in sortByYear!!.from!!..sortByYear!!.to!!) {
                            filteredMangas.add(el)
                        }
                    } else if (sortByYear?.from == null && sortByYear?.to != null) {
                        if (el.issue_year!! in 1111..sortByYear!!.to!!) {
                            filteredMangas.add(el)
                        }
                    } else if (sortByYear?.from != null && sortByYear?.to == null) {
                        if (el.issue_year!! in sortByYear!!.from!!..9999) {
                            filteredMangas.add(el)
                        }
                    } else {
                        filteredMangas.addAll(manga)
                    }
                }
            }
        } else {
            filteredMangas.addAll(manga)
        }
        mangaAdapter.submitList(filteredMangas.toList())
        binding.progressBar.gone()
    }

    private fun onItemClick(id: String) {
        openMangaDetails(id)
    }
}