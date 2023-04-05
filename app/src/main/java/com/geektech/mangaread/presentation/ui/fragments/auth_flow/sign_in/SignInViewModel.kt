package com.geektech.mangaread.presentation.ui.fragments.auth_flow.sign_in

import com.geektech.domain.model.LoginRequest
import com.geektech.domain.model.LoginResponse
import com.geektech.domain.use_cases.auth.UserLoginUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel(
    private val userLoginUseCase: UserLoginUseCase,
) : BaseViewModel() {


    private val _getUserLoginState = MutableStateFlow<UIState<LoginResponse>>(UIState.Empty())
    val getUserLoginState = _getUserLoginState.asStateFlow()

    fun userLogin(loginRequest: LoginRequest) {
        userLoginUseCase.invoke(loginRequest).collectFlow(_getUserLoginState)
    }
}