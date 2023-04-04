package com.geektech.mangaread.presentation.ui.fragments.main_flow.main.all_manga

import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.SortByIssueYear
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.utils.DataSendClass
import com.geektech.mangaread.databinding.FragmentAllMangaBinding
import com.geektech.mangaread.presentation.ui.adapters.MangaPagingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllMangaFragment(private val openMangaDetails: (id: String) -> Unit) :
    BaseFragment<FragmentAllMangaBinding, AllMangaViewModel>(R.layout.fragment_all_manga),
    DataSendClass.SearchBy {

    override val binding by viewBinding(FragmentAllMangaBinding::bind)
    override val viewModel by viewModel<AllMangaViewModel>()

    private var sortByYear: SortByIssueYear? = null

    private val mangaAdapter: MangaPagingAdapter by lazy {
        MangaPagingAdapter(this::onItemClick)
    }

    override fun initialize() {
        DataSendClass.instance?.setListener(this)

        binding.rvAllManga.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mangaAdapter
        }
    }

    override fun setupRequest() {
        viewModel.getManga().spectatePaging {
            mangaAdapter.submitData(it)
        }
    }

    private fun onItemClick(id: String) {
        openMangaDetails(id)
    }

    override fun searchBy(text: String) {
        viewModel.getManga(search = text).spectatePaging { data ->
            mangaAdapter.submitData(data)
        }
    }

    override fun getFilterData(
        types: List<String>?,
        genres: List<String>?,
        sortByIssueYear: SortByIssueYear?
    ) {
        sortByYear = sortByIssueYear

        viewModel.getManga(type = types, genreTitle = genres).spectatePaging { data ->
            mangaAdapter.submitData(data)
        }
    }
}
