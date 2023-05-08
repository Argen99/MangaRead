package com.geektech.mangaread.presentation.ui.fragments.auth_flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.geektech.domain.model.LoginRequest
import com.geektech.domain.model.LoginResponse
import com.geektech.domain.model.User
import com.geektech.domain.use_cases.auth.UserLoginUseCase
import com.geektech.domain.use_cases.auth.UserRegisterUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.RequestBody

class AuthViewModel(
    private val userLoginUseCase: UserLoginUseCase,
    private val registerUserUseCase: UserRegisterUseCase,
) : BaseViewModel() {

    private val _getUserLoginState = MutableStateFlow<UIState<LoginResponse>>(UIState.Empty())
    val getUserLoginState = _getUserLoginState.asStateFlow()

    private val _getRegisterState = MutableStateFlow<UIState<User>>(UIState.Empty())
    val getRegisterState = _getRegisterState.asStateFlow()

    private val _pagerCurrentItem = MutableLiveData<Int>()
    val pagerCurrentItem = _pagerCurrentItem.asFlow()

    fun userLogin(loginRequest: LoginRequest) {
        userLoginUseCase.invoke(loginRequest).collectFlow(_getUserLoginState)
    }

    fun userRegister(
        username: RequestBody,
        nickname: RequestBody,
        imageFile: RequestBody,
        password: RequestBody
    ) {
        registerUserUseCase.invoke(
            username = username, nickname = nickname,
            imageFile = imageFile, password = password
        ).collectFlow(_getRegisterState)
    }

    fun setCurrentPagerItem(index: Int) {
        if (index > 0)
            _pagerCurrentItem.value = index
    }
}