package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.akinpelumi.c2068220.mytu.common.ext.Constants.INVALID_EMAIL
import com.akinpelumi.c2068220.mytu.common.ext.Constants.INVALID_PASSWORD
import com.akinpelumi.c2068220.mytu.common.ext.Constants.NO_PASSWORD_MATCH
import com.akinpelumi.c2068220.mytu.common.ext.isValidEmail
import com.akinpelumi.c2068220.mytu.common.ext.isValidPassword
import com.akinpelumi.c2068220.mytu.common.ext.passwordMatches
import com.akinpelumi.c2068220.mytu.domain.model.Response
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.akinpelumi.c2068220.mytu.domain.repository.SendEmailVerificationResponse
import com.akinpelumi.c2068220.mytu.domain.repository.SignUpResponse
import com.akinpelumi.c2068220.mytu.ui.views.auth.signup.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val repo: AuthRepository,
) : MyTUViewModel(repo) {
  var uiState = mutableStateOf(SignUpUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }

  fun onRepeatPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(repeatPassword = newValue)
  }
  var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(false))
    private set
  var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(
    Response.Success(
      false
    )
  )
    private set

  fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
    signUpResponse = Response.Loading
    signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password)
  }

  fun sendEmailVerification() = viewModelScope.launch {
    sendEmailVerificationResponse = Response.Loading
    sendEmailVerificationResponse = repo.sendEmailVerification()
  }
  fun onSignUpClick() {
    if (!email.isValidEmail()) {
      uiState.value.isValidCredentials = false
      uiState.value.errorMsg = INVALID_EMAIL
      return
    }

    if (!password.isValidPassword()) {
      uiState.value.isValidCredentials = false
      uiState.value.errorMsg = INVALID_PASSWORD
      return
    }

    if (!password.passwordMatches(uiState.value.repeatPassword)) {
      uiState.value.isValidCredentials = false
      uiState.value.errorMsg = NO_PASSWORD_MATCH
      return
    }
    launchCatching {
      signUpResponse = Response.Loading
      signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password)
    }
  }
}
