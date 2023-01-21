package com.geektech.mangaread.presentation.ui.fragments.main.profile.edit_profile

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.FragmentEditProfileBinding
import com.geektech.mangaread.core.base.BaseFragment

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding,
        EditProfileViewModel>(R.layout.fragment_edit_profile) {

    override val binding by viewBinding(FragmentEditProfileBinding::bind)
    override val viewModel by viewModels<EditProfileViewModel>()

    override fun initialize() {

    }
}