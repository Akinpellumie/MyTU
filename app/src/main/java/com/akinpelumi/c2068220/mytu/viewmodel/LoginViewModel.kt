
package com.akinpelumi.c2068220.mytu.viewmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.akinpelumi.c2068220.mytu.common.ext.isValidEmail
import com.akinpelumi.c2068220.mytu.common.snackbar.SnackbarManager
import com.akinpelumi.c2068220.mytu.service.AccountService
import com.akinpelumi.c2068220.mytu.service.LogService
import com.akinpelumi.c2068220.mytu.showToast
import com.akinpelumi.c2068220.mytu.ui.navigations.HOME_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.LOGIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.MAIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.SIGN_UP_SCREEN
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.akinpelumi.c2068220.mytu.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val accountService: AccountService,
  logService: LogService
) : MyTUViewModel(logService), Parcelable {
  var uiState = mutableStateOf(LoginUiState())
    private set

  private val email
    get() = uiState.value.email
  private val password
    get() = uiState.value.password

  constructor(parcel: Parcel) : this(
    TODO("accountService"),
    TODO("logService")
  ) {
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
  fun onSignInClick(clearAndNavigate: (String) -> Unit) {
    //val context = LocalContext.current.applicationContext
    if (!email.isValidEmail()) {
      //SnackbarManager.showMessage(AppText.email_error)
      //showToast(context = , message = )
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
    clearAndNavigate(MAIN_SCREEN)
    //openAndPopUp(MAIN_SCREEN, LOGIN_SCREEN)
//    launchCatching {
//      accountService.authenticate(email, password)
//      clearAndNavigate(MAIN_SCREEN)
//    }
  }

  fun onForgotPasswordClick() {
    if (!email.isValidEmail()) {
      SnackbarManager.showMessage(AppText.email_error)
      return
    }

    launchCatching {
      accountService.sendRecoveryEmail(email)
      SnackbarManager.showMessage(AppText.recovery_email_sent)
    }
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {

  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<LoginViewModel> {
    override fun createFromParcel(parcel: Parcel): LoginViewModel {
      return LoginViewModel(parcel)
    }

    override fun newArray(size: Int): Array<LoginViewModel?> {
      return arrayOfNulls(size)
    }
  }
}
