package com.geektech.mangaread.presentation.ui.fragments.auth.sign_up

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.model.UserRegisterBody
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.loadImage
import com.geektech.mangaread.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpFragment() :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by viewModel<SignUpViewModel>()

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val pickedImage = data?.data
                if (pickedImage != null) {
                    binding.ivAvatar.loadImage(pickedImage.toString())
                }
            }
        }

    override fun initialize() {

    }

    override fun setupClickListeners() {
//        val tabs = activity?.findViewById<View>(R.id.tab_layout_auth) as TabLayout
        binding.tvSetAvatar.setOnClickListener {
            pickImage()
        }


        binding.tvSignIn.setOnClickListener {
            viewModel.registerUser(
                UserRegisterBody(
                    username = "asdasd12345",
                    nickname = "asdasd12345",
                    image_file = "adawdawdawdwd",
                    password = "12345678"
                )
            )
//            tabs.getTabAt(0)?.select()
        }

        viewModel.getUserRegisterState.collectState(
            onLoading = {
                Toast.makeText(requireContext(), "onLoading", Toast.LENGTH_SHORT).show()
            },
            onError = {
                Toast.makeText(requireContext(), "onError", Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                Toast.makeText(requireContext(), "onSuccess", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun pickImage() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            openGallery()
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(galleryIntent)
    }
}