package com.geektech.mangaread.presentation.ui.fragments.main.home.main.all_manga

import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.MangaResult
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.databinding.FragmentAllMangaBinding
import com.geektech.mangaread.presentation.ui.adapters.MangaAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllMangaFragment(private val openMangaDetails: (id: String) -> Unit) :
    BaseFragment<FragmentAllMangaBinding, AllMangaViewModel>(R.layout.fragment_all_manga) {

    override val binding by viewBinding(FragmentAllMangaBinding::bind)
    override val viewModel by viewModel<AllMangaViewModel>()

//    private val mangaList = arrayListOf<MangaResult>()
//    private val mangaAdapter: MangaAdapter by lazy {
//        MangaAdapter(mangaList, this::onItemClick)
//    }
//
//    override fun setupRequest() {
//        viewModel.getManga()
//    }
//
//    override fun initialize() {
//        binding.rvAllManga.apply {
//            layoutManager = GridLayoutManager(requireContext(), 2)
//            adapter = mangaAdapter
//        }
//    }
//
//    override fun setupObservers() {
//        viewModel.getMangaState.collectState(
//            onLoading = {},
//            onError = {},
//            onSuccess = {
//                mangaAdapter.submitList(it.results)
//            }
//        )
//    }
//
//    private fun onItemClick(id: String) {
//        openMangaDetails(id)
//    }
}

//________________________________________________________


//        binding.edit.addTextChangedListener {
//            viewModel.getMangaPg(search = it.toString()).spectatePaging(success = { data ->
//                mangaAdapter.submitData(data)
//                Toast.makeText(requireContext(), "asd", Toast.LENGTH_SHORT).show()
//            })
//        }


//        viewModel.getMangaPg().spectatePaging {
//            mangaAdapter.submitData(it)
//        }


        //        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(),
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    Toast.makeText(requireContext(), "Back", Toast.LENGTH_SHORT).show()
//                }
//            })
