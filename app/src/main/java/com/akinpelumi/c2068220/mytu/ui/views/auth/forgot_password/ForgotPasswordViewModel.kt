package com.akinpelumi.c2068220.mytu.ui.views.auth.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinpelumi.c2068220.mytu.domain.model.Response
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.akinpelumi.c2068220.mytu.domain.model.Response.*
import com.akinpelumi.c2068220.mytu.domain.repository.SendPasswordResetEmailResponse
import com.akinpelumi.c2068220.mytu.service.LogService
import com.akinpelumi.c2068220.mytu.ui.views.auth.signup.SignUpUiState
import com.akinpelumi.c2068220.mytu.viewmodel.MyTUViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repo: AuthRepository,
    logService: LogService
) : MyTUViewModel(logService, repo) {
    var sendPasswordResetEmailResponse by mutableStateOf<SendPasswordResetEmailResponse>(Success(false))
    var uiState = mutableStateOf(ForgotPasswordUiState())
        private set

    private val email
        get() = uiState.value.email


    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }
    fun sendPasswordResetEmail(){
        launchCatching {
            sendPasswordResetEmailResponse = Loading
            sendPasswordResetEmailResponse = repo.sendPasswordResetEmail(email)
        }
    }
}