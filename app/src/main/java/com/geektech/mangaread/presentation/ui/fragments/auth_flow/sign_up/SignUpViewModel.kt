package com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_up

import androidx.lifecycle.viewModelScope
import com.geektech.domain.model.CurrentUser
import com.geektech.domain.model.LoginRequest
import com.geektech.domain.model.LoginResponse
import com.geektech.domain.model.User
import com.geektech.domain.repositories.RoomRepository
import com.geektech.domain.use_cases.main.UserLoginUseCase
import com.geektech.domain.use_cases.main.UserRegisterUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class SignUpViewModel(
    private val registerUserUseCase: UserRegisterUseCase,
    private val userLoginUseCase: UserLoginUseCase,
    private val roomRepository: RoomRepository
) : BaseViewModel() {

    private val _getRegisterState = MutableStateFlow<UIState<User>>(UIState.Empty())
    val getRegisterState = _getRegisterState.asStateFlow()

    private val _getUserLoginState = MutableStateFlow<UIState<LoginResponse>>(UIState.Empty())
    val getUserLoginState = _getUserLoginState.asStateFlow()

    fun userRegister(
        username: RequestBody,
        nickname: RequestBody,
        imageFile: RequestBody,
        password: RequestBody,
        imageUri: String
    ) {
        registerUserUseCase.invoke(
            username = username, nickname = nickname,
            imageFile = imageFile, password = password,
            imageUri = imageUri
        ).collectFlow(_getRegisterState)
    }

    fun userLogin(loginRequest: LoginRequest) {
        userLoginUseCase.invoke(loginRequest).collectFlow(_getUserLoginState)
    }

    fun saveUser(user: CurrentUser) {
        viewModelScope.launch {
            roomRepository.saveUser(user)
        }
    }
}