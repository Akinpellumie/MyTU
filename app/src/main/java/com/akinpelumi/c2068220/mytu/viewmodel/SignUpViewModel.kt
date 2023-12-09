/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.akinpelumi.c2068220.mytu.common.ext.isValidEmail
import com.akinpelumi.c2068220.mytu.common.ext.isValidPassword
import com.akinpelumi.c2068220.mytu.common.ext.passwordMatches
import com.akinpelumi.c2068220.mytu.common.snackbar.SnackbarManager
import com.akinpelumi.c2068220.mytu.domain.model.Response
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.akinpelumi.c2068220.mytu.domain.repository.SendEmailVerificationResponse
import com.akinpelumi.c2068220.mytu.domain.repository.SignUpResponse
import com.akinpelumi.c2068220.mytu.service.LogService
import com.akinpelumi.c2068220.mytu.ui.views.auth.signup.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.akinpelumi.c2068220.mytu.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val repo: AuthRepository,
  logService: LogService
) : MyTUViewModel(logService, repo) {
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
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    if (!password.isValidPassword()) {
      SnackbarManager.showMessage(AppText.password_error)
      return
    }

    if (!password.passwordMatches(uiState.value.repeatPassword)) {
      SnackbarManager.showMessage(AppText.password_match_error)
      return
    }

    launchCatching {
      signUpResponse = Response.Loading
      signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password)
      //openAndPopUp(HOME_SCREEN, SIGN_UP_SCREEN)
    }
  }
}
