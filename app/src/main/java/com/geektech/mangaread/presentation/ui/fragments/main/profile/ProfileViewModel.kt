package com.geektech.mangaread.presentation.ui.fragments.main.profile

import androidx.lifecycle.viewModelScope
import com.geektech.domain.model.CurrentUser
import com.geektech.domain.repositories.RoomRepository
import com.geektech.domain.use_cases.auth.LogoutUseCase
import com.geektech.domain.use_cases.main.UserLoginUseCase
import com.geektech.mangaread.core.base.BaseViewModel
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val roomRepository: RoomRepository
) : BaseViewModel() {

    private val _getLogoutState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val getLogoutState = _getLogoutState.asStateFlow()

    fun logout(refresh: String) {
        logoutUseCase.invoke(refresh).collectFlow(_getLogoutState)
    }

    suspend fun getUser(): CurrentUser? {
        return roomRepository.getUser()
    }

    fun deleteUser() {
        viewModelScope.launch {
            roomRepository.deleteUser()
        }
    }
}