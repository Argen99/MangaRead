package com.geektech.mangaread.presentation.ui.fragments.main.home.main

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.FragmentMainBinding
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.utils.Constants
import com.geektech.mangaread.presentation.ui.adapters.MainPagerAdapter
import com.geektech.mangaread.presentation.ui.fragments.main.home.main.all_manga.AllMangaFragment
import com.geektech.mangaread.presentation.ui.fragments.main.home.main.top_manga.TopMangaFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel by viewModels<MainViewModel>()

    override fun initialize() {
        val adapter = MainPagerAdapter(requireActivity())
        adapter.addFragment(AllMangaFragment(this::openMangaDetails), getString(R.string.all_manga))
        adapter.addFragment(TopMangaFragment(), getString(R.string.top_100))
        binding.pager.adapter = adapter
        binding.pager.currentItem = 0

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }

    override fun setupClickListeners() {
        binding.btnSort.setOnClickListener {
            navigate(R.id.mangaDetailFragment)
        }
    }

    private fun openMangaDetails(id: String) {
        navigate(
            R.id.action_mainFragment_to_mangaDetailFragment,
            bundleOf(Constants.MANGA_ID_KEY to id)
        )
    }

    private fun onItemClick(id: String) {
//        navigate(R.id.action_mainFragment_to_mangaDetailFragment)
        Toast.makeText(requireContext(),  id, Toast.LENGTH_SHORT).show()
    }
}