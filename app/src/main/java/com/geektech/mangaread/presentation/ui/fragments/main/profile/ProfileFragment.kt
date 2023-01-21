package com.geektech.mangaread.presentation.ui.fragments.main.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.FragmentProfileBinding
import com.geektech.mangaread.core.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding,
        ProfileViewModel>(R.layout.fragment_profile) {

    override val binding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel by viewModels<ProfileViewModel>()

    override fun setupClickListeners() {
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.editProfileFragment)
        }
    }
}