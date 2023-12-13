
package com.akinpelumi.c2068220.mytu.viewmodel

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.akinpelumi.c2068220.mytu.common.ext.isValidEmail
import com.akinpelumi.c2068220.mytu.domain.model.Response
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.akinpelumi.c2068220.mytu.domain.repository.SignInResponse
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepository
) : MyTUViewModel(repo) {
  var uiState = mutableStateOf(LoginUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password

  var signInResponse by mutableStateOf<SignInResponse>(Response.Success(false))
    private set

  fun onEmailChange(newValue: String) {
    uiState.value = uiState.value.copy(email = newValue)
  }

  fun onPasswordChange(newValue: String) {
    uiState.value = uiState.value.copy(password = newValue)
  }

  fun onSignInClick() {
    if (!email.isValidEmail()) {
      uiState.value.userIsAuthenticated = false
      uiState.value.error = "Please Insert a valid email"
      return
    }

    if (password.isBlank()) {
      uiState.value.userIsAuthenticated = false
      uiState.value.error = "Password cannot be empty"
      return
    }
    //start auth progress
    uiState.value.inProgress = true
    launchCatching {
      signInResponse = Response.Loading
      signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
    }
  }
  fun onSignInWithMicrosoftClick(activity: Activity){
    //start auth progress
    uiState.value.inProgress = true
    launchCatching {
      signInResponse = Response.Loading
      signInResponse = repo.firebaseSignInWithMicrosoft(activity)
    }
  }

}
