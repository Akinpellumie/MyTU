package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.akinpelumi.c2068220.mytu.domain.model.Response.Loading
import com.akinpelumi.c2068220.mytu.domain.model.Response.Success
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.akinpelumi.c2068220.mytu.domain.repository.SendPasswordResetEmailResponse
import com.akinpelumi.c2068220.mytu.ui.views.auth.forgot_password.ForgotPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repo: AuthRepository
) : MyTUViewModel(repo) {
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