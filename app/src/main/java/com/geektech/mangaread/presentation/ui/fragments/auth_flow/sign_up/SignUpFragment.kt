package com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_up

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.domain.model.LoginRequest
import com.geektech.domain.model.LoginResponse
import com.geektech.domain.model.User
import com.geektech.mangaread.R
import com.geektech.mangaread.core.base.BaseFragment
import com.geektech.mangaread.core.extensions.gone
import com.geektech.mangaread.core.extensions.loadImage
import com.geektech.mangaread.core.extensions.showToast
import com.geektech.mangaread.core.extensions.visible
import com.geektech.mangaread.core.utils.Constants.READ_EXTERNAL_STORAGE_REQUEST_CODE
import com.geektech.mangaread.core.utils.RealPathUtil
import com.geektech.mangaread.databinding.FragmentSignUpBinding
import com.geektech.mangaread.presentation.ui.fragments.auth_flow.AuthViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.io.File

class SignUpFragment(private val signIn: () -> Unit) :
    BaseFragment<FragmentSignUpBinding, AuthViewModel>(R.layout.fragment_sign_up) {

    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by activityViewModel<AuthViewModel>()

    private val tokenManager: TokenManager by inject()
    private var imageUriPath: String? = null
    private lateinit var etUserName: String
    private lateinit var etPassword: String

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val pickedImage = data?.data
                if (pickedImage != null) {
                    imageUriPath = RealPathUtil.getRealPathFromURI(requireContext(), pickedImage)!!
                    binding.ivAvatar.loadImage(pickedImage.toString())
                }
            }
        }

    override fun setupClickListeners() {
        binding.tvSetAvatar.setOnClickListener {
            pickImage()
        }

        binding.btnSignUp.setOnClickListener {
            registerUser()
        }

        binding.tvSignIn.setOnClickListener {
            viewModel.setCurrentPagerItem(0)
        }
    }

    override fun setupObservers() {
        viewModel.getRegisterState.collectState(
            onError = {
                context?.showToast(it)
            },
            onSuccess = {
                successSingUp(it)
            },
            onLoading = {
                binding.progressBar.visible()
            }
        )

        viewModel.getUserLoginState.collectState(
            onError = { message ->
                context?.showToast(message)
            },
            onSuccess = {
                successSignIn(it)
            },
            onLoading = {
                binding.progressBar.visible()
            }
        )
    }

    private fun successSignIn(loginResponse: LoginResponse) {
        tokenManager.saveAccessToken(loginResponse.access)
        tokenManager.saveRefreshToken(loginResponse.refresh)
        signIn()
    }

    private fun successSingUp(it: User) {
        binding.progressBar.gone()
        context?.showToast(it.message)
        viewModel.userLogin(
            LoginRequest(
                username = etUserName,
                password = etPassword
            )
        )
    }

    private fun registerUser() {
        etUserName = binding.etUsername.text.toString()
        val etNickname = binding.etNickname.text.toString()
        etPassword = binding.etPassword.text.toString()
        val etConfirmPassword = binding.etConfirmPassword.text.toString()

        if (etUserName.length < 10) {
            binding.etUsername.error = getString(R.string.invalid_username)
        } else if (etNickname.length < 10) {
            binding.etNickname.error = getString(R.string.invalid_nickname)
        } else if (etPassword.length < 8) {
            binding.etPassword.error = getString(R.string.invalid_password1)
        } else if (etConfirmPassword != etPassword) {
            binding.etPassword.error = getString(R.string.invalid_password)
        } else if (imageUriPath == null) {
            context?.showToast(getString(R.string.add_avatar))
        } else {
            val file = File(imageUriPath!!)

            val imageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val username =
                binding.etUsername.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val nickname =
                binding.etNickname.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val password =
                binding.etPassword.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())

            viewModel.userRegister(
                username = username,
                imageFile = imageFile,
                nickname = nickname,
                password = password
            )
        }
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
                READ_EXTERNAL_STORAGE_REQUEST_CODE
            )
        } else {
            openGallery()
        }
    }

    private fun openGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        resultLauncher.launch(galleryIntent)
    }
}