package com.geektech.mangaread.presentation.ui.fragments.main_flow.main

import android.app.Dialog
import android.view.Gravity
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.data.local_db.prefs.SelectedItemsPrefs
import com.geektech.domain.model.Genres
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.navigateSafely
import com.geektech.mangaread.core.extensions.showToast
import com.geektech.mangaread.core.utils.Constants
import com.geektech.mangaread.databinding.FilterLayoutBinding
import com.geektech.mangaread.databinding.FragmentMainBinding
import com.geektech.mangaread.databinding.GenreLayoutBinding
import com.geektech.mangaread.presentation.ui.adapters.GenreAdapter
import com.geektech.mangaread.presentation.ui.adapters.MainPagerAdapter
import com.geektech.mangaread.presentation.ui.adapters.TypesAdapter
import com.geektech.mangaread.presentation.ui.fragments.main_flow.main.all_manga.AllMangaFragment
import com.geektech.mangaread.presentation.ui.fragments.main_flow.main.top_manga.TopMangaFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel by activityViewModel<MainViewModel>()

    private var typesList = listOf<String>()
    private val genreList = listOf<Genres>()

    private var topMangaSelectedTypes: List<String>? = null
    private var topMangaSelectedGenres: List<String>? = null

    private var allMangaSelectedTypes: List<String>? = null
    private var allMangaSelectedGenres: List<String>? = null

    private val selectedItemsPrefs: SelectedItemsPrefs by inject()

    private val allMangaGenreAdapter: GenreAdapter by lazy {
        GenreAdapter(requireContext(), R.layout.genre_layout, genreList)
    }
    private val allMangaTypesAdapter: TypesAdapter by lazy {
        TypesAdapter(requireContext(), R.layout.filter_layout, typesList, selectedItemsPrefs)
    }

    private val topMangaGenreAdapter: GenreAdapter by lazy {
        GenreAdapter(requireContext(), R.layout.genre_layout, genreList)
    }
    private val topMangaTypesAdapter: TypesAdapter by lazy {
        TypesAdapter(requireContext(), R.layout.filter_layout, typesList, selectedItemsPrefs)
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

        filterBinding.lvTypes.adapter = when (binding.pager.currentItem) {
            0 -> {
                allMangaTypesAdapter
            }
            else -> {
                topMangaTypesAdapter
            }
        }
        allMangaTypesAdapter

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

    private fun performFiltering(
        filterBinding: FilterLayoutBinding,
        dialog: Dialog
    ) {
        when (binding.pager.currentItem) {
            0 -> {
                allMangaSelectedTypes = allMangaTypesAdapter.getSelectedItems()
//                val sortByIssueYear = SortByIssueYear(null, null)
//                if (filterBinding.etFrom.text.toString().isNotEmpty()) {
//                    if (filterBinding.etFrom.text.toString().length < 4) {
//                        filterBinding.etFrom.error = getString(R.string.min_length)
//                    } else {
//                        sortByIssueYear.from = filterBinding.etFrom.text.toString().toInt()
//                    }
//                }
//
//                if (filterBinding.etTo.text.toString().isNotEmpty()) {
//                    if (filterBinding.etTo.text.toString().length < 4) {
//                        filterBinding.etTo.error = getString(R.string.min_length)
//                    } else {
//                        sortByIssueYear.to = filterBinding.etTo.text.toString().toInt()
//                    }
//                }
                viewModel.allMangaFilterBy(allMangaSelectedTypes, allMangaSelectedGenres)
                dialog.dismiss()

                requireActivity().showToast("Paging ${allMangaSelectedTypes?.joinToString()} , ${allMangaSelectedGenres?.joinToString()}")
            }
            else -> {
                topMangaSelectedTypes = topMangaTypesAdapter.getSelectedItems()
                viewModel.getTopManga(
                    type = topMangaSelectedTypes,
                    genreTitle = topMangaSelectedGenres
                )

                requireActivity().showToast("Top ${topMangaSelectedTypes?.joinToString()} , ${topMangaSelectedGenres?.joinToString()}")
            }
        }
    }

    private fun showGenre() {
        viewModel.getGenres()

        val genreDialog = Dialog(requireContext())
        genreDialog.window?.setGravity(Gravity.TOP or Gravity.END)
        val genreBinding = GenreLayoutBinding.inflate(layoutInflater)
        genreDialog.setContentView(genreBinding.root)
        genreDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        genreBinding.lvGenres.adapter = when (binding.pager.currentItem) {
            0 -> {
                allMangaGenreAdapter
            }
            else -> {
                topMangaGenreAdapter
            }
        }

        genreBinding.btnDismiss.setOnClickListener {
            genreDialog.dismiss()
        }

        genreBinding.btnApply.setOnClickListener {
            when (binding.pager.currentItem) {
                0 -> {
                    allMangaSelectedGenres = allMangaGenreAdapter.getSelectedItems()
                }
                else -> {
                    topMangaSelectedGenres = topMangaGenreAdapter.getSelectedItems()
                }
            }
            genreDialog.dismiss()
        }

        genreDialog.show()
    }

    override fun setupObservers() {
        binding.etSearch.addTextChangedListener {
            if (binding.pager.currentItem == 0) {
                viewModel.allMangaSearchBy(it.toString())
            } else {
                viewModel.getTopManga(search = it.toString())
            }
        }

        viewModel.getGenresState.collectState(
            {},
            onError = { context?.showToast(it) },
            onSuccess = {
                allMangaGenreAdapter.submitList(it)
                topMangaGenreAdapter.submitList(it)
            }
        )
    }

    private fun openMangaDetails(id: String) {
        findNavController().navigateSafely(
            R.id.action_mainFragment_to_mangaDetailFragment,
            bundleOf(Constants.MANGA_ID_KEY to id)
        )
    }
}