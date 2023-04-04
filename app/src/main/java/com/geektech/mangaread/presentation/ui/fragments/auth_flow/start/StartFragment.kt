package com.geektech.mangaread.presentation.ui.fragments.auth_flow.start

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.extensions.navigateSafely
import com.geektech.mangaread.databinding.FragmentStartBinding
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.authorization.AuthorizationFragment

class StartFragment : BaseFragment<FragmentStartBinding, BaseViewModel>(R.layout.fragment_start) {

    override val binding by viewBinding(FragmentStartBinding::bind)
    override lateinit var viewModel: BaseViewModel

    override fun setupClickListeners() {
        binding.btnSignIn.setOnClickListener {
            findNavController().navigateSafely(R.id.action_startFragment_to_authorizationFragment,
                bundleOf(AuthorizationFragment.FRAGMENT_KEY to 0))
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigateSafely(R.id.action_startFragment_to_authorizationFragment,
                bundleOf(AuthorizationFragment.FRAGMENT_KEY to 1))
        }
    }
}