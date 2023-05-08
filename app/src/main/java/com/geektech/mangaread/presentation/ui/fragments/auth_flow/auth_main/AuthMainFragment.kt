package com.geektech.mangaread.presentation.ui.fragments.auth_flow.auth_main

import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.activityNavController
import com.geektech.mangaread.core.extensions.navigateSafely
import com.geektech.mangaread.core.utils.Constants.AUTH_CURRENT_ITEM_INDEX
import com.geektech.mangaread.databinding.FragmentAuthorizationBinding
import com.geektech.mangaread.presentation.ui.adapters.AuthorizationPagerAdapter
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.AuthViewModel
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_in.SignInFragment
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_up.SignUpFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AuthMainFragment : BaseFragment<FragmentAuthorizationBinding,
        AuthViewModel>(R.layout.fragment_authorization) {

    override val binding by viewBinding(FragmentAuthorizationBinding::bind)
    override val viewModel by activityViewModel<AuthViewModel>()
    private lateinit var fragmentAdapter: AuthorizationPagerAdapter

    override fun initialize() {
        val currentItemIndex = arguments?.getInt(AUTH_CURRENT_ITEM_INDEX) ?: 0

        fragmentAdapter = AuthorizationPagerAdapter(requireActivity())
        fragmentAdapter.addFragment(SignInFragment(this::btnLogin), getString(R.string.login))
        fragmentAdapter.addFragment(
            SignUpFragment(this::btnLogin),
            getString(R.string.btn_txt_registration)
        )
        binding.pagerAuth.adapter = fragmentAdapter
        binding.pagerAuth.post {
            binding.pagerAuth.setCurrentItem(currentItemIndex, false)
        }

        TabLayoutMediator(binding.tabLayoutAuth, binding.pagerAuth) { tab, position ->
            tab.text = fragmentAdapter.getTabTitle(position)
        }.attach()
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pagerCurrentItem.collectLatest {
                binding.pagerAuth.currentItem = it
            }
        }
    }

    private fun btnLogin() {
        activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
    }
}