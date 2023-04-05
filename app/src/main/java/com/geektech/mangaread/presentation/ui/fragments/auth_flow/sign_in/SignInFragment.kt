package com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_in

import android.view.View
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.domain.model.LoginRequest
import com.geektech.domain.model.LoginResponse
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.gone
import com.geektech.mangaread.core.extensions.showToast
import com.geektech.mangaread.core.extensions.visible
import com.geektech.mangaread.databinding.FragmentSignInBinding
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment(private val signIn: () -> Unit) :
    BaseFragment<FragmentSignInBinding, SignInViewModel>(
        R.layout.fragment_sign_in
    ) {
    override val binding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel by viewModel<SignInViewModel>()

    private val tokenManager: TokenManager by inject()
    private var tabs: TabLayout? = null

    override fun initialize() {
         tabs = activity?.findViewById<View>(R.id.tab_layout_auth) as TabLayout
    }

    override fun setupClickListeners() {
        binding.btnSignIn.setOnClickListener {
            userLogin()
        }

        viewModel.getUserLoginState.collectState(
            onLoading = {
                binding.progressBarSif.visible()
            },
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                binding.progressBarSif.gone()
                context?.showToast(it)
            }
        )

        binding.tvRegister.setOnClickListener {
            tabs?.let { it.getTabAt(1)?.select() }
        }
    }

    private fun userLogin() = with(binding) {
        if (etUserName.text.toString().length < 10) {
            etUserName.error = getString(R.string.invalid_nickname)
        } else if (etUserPassword.text.toString().length < 8) {
            etUserPassword.error = getString(R.string.invalid_password1)
        } else {
            viewModel.userLogin(
                LoginRequest(
                    username = etUserName.text.toString(),
                    password = etUserPassword.text.toString()
                )
            )
        }
    }

    private fun onSuccess(it: LoginResponse) {
        binding.progressBarSif.gone()
        tokenManager.saveAccessToken(it.access)
        tokenManager.saveRefreshToken(it.refresh)
        signIn()
    }
}
