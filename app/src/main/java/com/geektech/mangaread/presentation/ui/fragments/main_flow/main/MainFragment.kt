package com.geektech.mangaread.presentation.ui.fragments.main_flow.main

import android.app.Dialog
import android.view.Gravity
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.Genres
import com.geektech.domain.model.SortByIssueYear
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.showToast
import com.geektech.mangaread.core.utils.Constants
import com.geektech.mangaread.core.utils.DataSendClass
import com.geektech.mangaread.databinding.FilterLayoutBinding
import com.geektech.mangaread.databinding.FragmentMainBinding
import com.geektech.mangaread.databinding.GenreLayoutBinding
import com.geektech.mangaread.presentation.ui.adapters.GenreAdapter
import com.geektech.mangaread.presentation.ui.adapters.MainPagerAdapter
import com.geektech.mangaread.presentation.ui.adapters.TypesAdapter
import com.geektech.mangaread.presentation.ui.fragments.main_flow.main.all_manga.AllMangaFragment
import com.geektech.mangaread.presentation.ui.fragments.main_flow.main.top_manga.TopMangaFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment() : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel by viewModel<MainViewModel>()

    private var typesList = listOf<String>()
    private val genreList = listOf<Genres>()

    private var selectedTypes: List<String>? = null
    private var selectedGenres: List<String>? = null

    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter(requireContext(),R.layout.genre_layout,genreList)
    }
    private val typesAdapter: TypesAdapter by lazy {
        TypesAdapter(requireContext(),R.layout.filter_layout, typesList)
    }

    override fun initialize() {
        val pagerAdapter = MainPagerAdapter(requireActivity())

        pagerAdapter.addFragment(
            AllMangaFragment(this::openMangaDetails),
            getString(R.string.all_manga)
        )
        pagerAdapter.addFragment(
            TopMangaFragment(this::openMangaDetails),
            getString(R.string.top_100)
        )
        binding.pager.apply {
            adapter = pagerAdapter
            currentItem = 0
        }

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = pagerAdapter.getTabTitle(position)
        }.attach()
    }

    override fun setupClickListeners() {
        binding.btnFilter.setOnClickListener {
            showFilter()
        }
    }

    private fun showFilter() {
        val dialog = Dialog(requireContext())
        dialog.window?.setGravity(Gravity.TOP or Gravity.END)
        val filterBinding = FilterLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(filterBinding.root)
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        typesList = resources.getStringArray(R.array.types).toList()

        filterBinding.lvTypes.adapter = typesAdapter

        filterBinding.btnDismiss.setOnClickListener {
            dialog.dismiss()
        }

        filterBinding.btnApply.setOnClickListener {
            performFiltering(filterBinding, dialog)
        }

        filterBinding.showGenre.setOnClickListener {
           showGenre()
        }

        filterBinding.btnReset.setOnClickListener {
            dialog.hide()
        }

        dialog.show()
    }

    private fun performFiltering (
        filterBinding: FilterLayoutBinding,
        dialog: Dialog
    ) {
        val sortByIssueYear = SortByIssueYear(null, null)
        if (filterBinding.etFrom.text.toString().isNotEmpty()) {
            if (filterBinding.etFrom.text.toString().length < 4) {
                filterBinding.etFrom.error = getString(R.string.min_length)
            } else {
                sortByIssueYear.from = filterBinding.etFrom.text.toString().toInt()
            }
        }

        if (filterBinding.etTo.text.toString().isNotEmpty()) {
            if (filterBinding.etTo.text.toString().length < 4) {
                filterBinding.etTo.error = getString(R.string.min_length)
            } else {
                sortByIssueYear.to = filterBinding.etTo.text.toString().toInt()
            }
        }

        selectedTypes = typesAdapter.getSelectedItems()
        DataSendClass.instance?.sendFilterData(selectedTypes, selectedGenres, sortByIssueYear)
        dialog.dismiss()
        binding.tv.text = "Sort by: type:${selectedTypes.toString()}, genre:${selectedGenres}"
        typesAdapter.clearSelectedItems()
        genreAdapter.clearSelectedItems()
    }

    private fun showGenre(){
        viewModel.getGenres()

        val genreDialog = Dialog(requireContext())
        genreDialog.window?.setGravity(Gravity.TOP or Gravity.END)
        val genreBinding = GenreLayoutBinding.inflate(layoutInflater)
        genreDialog.setContentView(genreBinding.root)
        genreDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        genreBinding.lvGenres.adapter = genreAdapter

        genreBinding.btnDismiss.setOnClickListener {
            genreDialog.dismiss()
        }

        genreBinding.btnApply.setOnClickListener {
            selectedGenres = genreAdapter.getSelectedItems()
            genreDialog.dismiss()
        }

        genreDialog.show()
    }

    override fun setupObservers() {
        binding.etSearch.addTextChangedListener {
            DataSendClass.instance?.search(it.toString())
        }

        viewModel.getGenresState.collectState(
            onLoading = {

            },
            onError = {
                context?.showToast(it)
            },
            onSuccess = {
                genreAdapter.submitList(it)
            }
        )
    }

    private fun openMangaDetails(id: String) {
        navigate(
            R.id.action_mainFragment_to_mangaDetailFragment,
            bundleOf(Constants.MANGA_ID_KEY to id)
        )
    }
}