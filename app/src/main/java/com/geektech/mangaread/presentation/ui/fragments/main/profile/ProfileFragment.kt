package com.geektech.mangaread.presentation.ui.fragments.main.profile

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.data.core.utils.Constants
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.data.local_db.room.CurrentUserDao
import com.geektech.domain.model.CurrentUser
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.gone
import com.geektech.mangaread.core.extensions.loadImage
import com.geektech.mangaread.core.extensions.showToast
import com.geektech.mangaread.core.extensions.visible
import com.geektech.mangaread.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding,
        ProfileViewModel>(R.layout.fragment_profile) {

    override val binding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel by viewModel<ProfileViewModel>()

    private var user: CurrentUser? = null

    private val tokenManager: TokenManager by inject()

    override fun initialize() {
        viewLifecycleOwner.lifecycleScope.launch {
            user = viewModel.getUser()
            user?.let { setData(user!!) }
        }
    }

    override fun setupClickListeners() {
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.editProfileFragment)
        }

        binding.btnLogout.setOnClickListener {
            tokenManager.getRefreshToken()?.let { viewModel.logout(it) }
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        Log.e("ololo", tokenManager.getRefreshToken().toString())
        Log.e("ololo", tokenManager.getAccessToken().toString())
    }

    override fun setupObservers() {
        viewModel.getLogoutState.collectState(
            onLoading = {
                binding.progressBarPf.visible()
            },
            onSuccess = {
                successLogout()
            },
            onError = {
                binding.progressBarPf.gone()
                context?.showToast(it)
            }
        )
    }

    private fun successLogout() {
        context?.showToast(getString(R.string.successful_logout))
        binding.progressBarPf.gone()
        tokenManager.clearPrefs()
        viewModel.deleteUser()
        navigate(R.id.action_profileFragment_to_startFragment)
    }

    private fun setData(user: CurrentUser) = with(binding) {
        ivUserAvatar.loadImage(user.image)
        tvUsername.text = user.username
        tvUserPassword.text = user.password
        tvFullName.text = user.fullname ?: getString(R.string.default_user_name)
    }
}