package com.geektech.mangaread.presentation.ui.fragments.auth.sign_in

import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.FragmentSignInBinding
import com.geektech.mangaread.core.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class SignInFragment(private val signIn: () -> Unit) : BaseFragment<FragmentSignInBinding, SignInViewModel>(
    R.layout.fragment_sign_in
) {
    override val binding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel by viewModels<SignInViewModel>()

    override fun initialize() {

    }

    override fun setupClickListeners() {
        val tabs = activity?.findViewById<View>(R.id.tab_layout_auth) as TabLayout
        binding.btnSignIn.setOnClickListener {
            signIn()
        }

        binding.tvRegister.setOnClickListener {
            tabs.getTabAt(1)?.select()
        }
    }
}
