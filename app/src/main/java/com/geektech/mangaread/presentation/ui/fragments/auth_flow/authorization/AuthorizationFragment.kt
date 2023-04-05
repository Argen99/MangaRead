package com.geektech.mangaread.presentation.ui.fragments.auth_flow.authorization

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.FragmentAuthorizationBinding
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.activityNavController
import com.geektech.mangaread.core.extensions.navigateSafely
import com.geektech.mangaread.presentation.ui.adapters.AuthorizationPagerAdapter
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_in.SignInFragment
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_up.SignUpFragment
import com.google.android.material.tabs.TabLayoutMediator

class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding,
        AuthorizationViewModel>(R.layout.fragment_authorization) {

    override val binding by viewBinding(FragmentAuthorizationBinding::bind)
    override val viewModel by viewModels<AuthorizationViewModel>()
    private lateinit var fragmentAdapter: AuthorizationPagerAdapter

    override fun initialize() {
        val currentItemIndex = arguments?.getInt(FRAGMENT_KEY) ?: 0

        fragmentAdapter = AuthorizationPagerAdapter(requireActivity())
        fragmentAdapter.addFragment(SignInFragment(this::btnLogin), getString(R.string.login))
        fragmentAdapter.addFragment(SignUpFragment(this::btnLogin), getString(R.string.btn_txt_registration))
        binding.pagerAuth.adapter = fragmentAdapter
        binding.pagerAuth.post {
            binding.pagerAuth.setCurrentItem(currentItemIndex, false)
        }

        TabLayoutMediator(binding.tabLayoutAuth, binding.pagerAuth) { tab, position ->
            tab.text = fragmentAdapter.getTabTitle(position)
        }.attach()
    }

    private fun btnLogin() {
        activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
    }

    companion object {
        const val FRAGMENT_KEY = "fragmentKey"
    }
}