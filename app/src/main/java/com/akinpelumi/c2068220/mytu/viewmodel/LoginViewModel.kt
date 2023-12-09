
package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.akinpelumi.c2068220.mytu.common.ext.isValidEmail
import com.akinpelumi.c2068220.mytu.common.snackbar.SnackbarManager
import com.akinpelumi.c2068220.mytu.domain.model.Response
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.akinpelumi.c2068220.mytu.domain.repository.SignInResponse
import com.akinpelumi.c2068220.mytu.service.LogService
import com.akinpelumi.c2068220.mytu.ui.navigations.LOGIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.SIGN_UP_SCREEN
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.akinpelumi.c2068220.mytu.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
  logService: LogService, private val repo: AuthRepository
) : MyTUViewModel(logService,repo) {
  var uiState = mutableStateOf(LoginUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password

//  constructor(parcel: Parcel) : this(
//    TODO("accountService"),
//    TODO("logService")
//  ) {
//  }
  var signInResponse by mutableStateOf<SignInResponse>(Response.Success(false))
    private set

  fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
    signInResponse = Response.Loading
    signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
  }

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }
fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
  openAndPopUp(SIGN_UP_SCREEN, LOGIN_SCREEN)
}
  fun onSignInClick() {
    if (!email.isValidEmail()) {
      uiState.value.userIsAuthenticated = false
      uiState.value.error = "Please Insert a valid email"
      return
    }

    if (password.isBlank()) {
      //SnackbarManager.showMessage(AppText.empty_password_error)
      uiState.value.userIsAuthenticated = false
      uiState.value.error = "Password cannot be empty"
      return
    }
    //start auth progress
    uiState.value.inProgress = true
    launchCatching {
      //signInWithEmailAndPassword(email, password)
      signInResponse = Response.Loading
      signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
      //clearAndNavigate(MAIN_SCREEN)
    }
  }

  fun onForgotPasswordClick() {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    launchCatching {
      //accountService.sendRecoveryEmail(email)
      SnackbarManager.showMessage(AppText.recovery_email_sent)
    }
  }

}
