package com.geektech.mangaread.presentation.ui.fragments.auth.sign_up

import com.geektech.domain.model.User
import com.geektech.domain.model.UserRegisterBody
import com.geektech.domain.use_cases.RegisterUserUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : BaseViewModel() {

    private val _getUserRegisterState = MutableStateFlow<UIState<User>>(UIState.Empty())
    val getUserRegisterState = _getUserRegisterState.asStateFlow()

    fun registerUser(body: UserRegisterBody) {
        registerUserUseCase(body).collectFlow(_getUserRegisterState)
    }
}