package com.geektech.mangaread.presentation.ui.fragments.auth_flow

import androidx.activity.addCallback
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.FragmentStartBinding
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.authorization.AuthorizationFragment

class StartFragment : BaseFragment<FragmentStartBinding, BaseViewModel>(R.layout.fragment_start) {

    override val binding by viewBinding(FragmentStartBinding::bind)
    override lateinit var viewModel: BaseViewModel

    override fun initialize() {

    }

    override fun setupClickListeners() {
        binding.btnSignIn.setOnClickListener {
            navigate(R.id.action_startFragment_to_authorizationFragment,
                bundleOf(AuthorizationFragment.FRAGMENT_KEY to 0))
        }
        binding.btnSignUp.setOnClickListener {
            navigate(R.id.action_startFragment_to_authorizationFragment,
                bundleOf(AuthorizationFragment.FRAGMENT_KEY to 1))
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
            return@addCallback
        }
    }
}