//
//package com.akinpelumi.c2068220.mytu.ui.views.splash
//
//import androidx.compose.runtime.mutableStateOf
//import com.akinpelumi.c2068220.mytu.service.AccountService
//import com.akinpelumi.c2068220.mytu.service.ConfigurationService
//import com.akinpelumi.c2068220.mytu.service.LogService
//import com.akinpelumi.c2068220.mytu.ui.navigations.LOGIN_SCREEN
//import com.akinpelumi.c2068220.mytu.ui.navigations.SPLASH_SCREEN
//import com.akinpelumi.c2068220.mytu.viewmodel.MyTUViewModel
//import com.google.firebase.auth.FirebaseAuthException
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
//@HiltViewModel
//class SplashViewModel @Inject constructor(
//  configurationService: ConfigurationService,
//  private val accountService: AccountService,
//  logService: LogService
//) : MyTUViewModel(logService, null) {
//  val showError = mutableStateOf(false)
//
//  init {
//    launchCatching { configurationService.fetchConfiguration() }
//  }
//
//  fun onAppStart(openAndPopUp: (String, String) -> Unit) {
//
//    showError.value = false
//    if (accountService.hasUser) openAndPopUp(LOGIN_SCREEN, SPLASH_SCREEN)
//    else createAnonymousAccount(openAndPopUp)
//  }
//
//  private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
//    launchCatching(snackbar = false) {
//      try {
//        accountService.createAnonymousAccount()
//      } catch (ex: FirebaseAuthException) {
//        showError.value = true
//        throw ex
//      }
//      openAndPopUp(LOGIN_SCREEN, SPLASH_SCREEN)
//    }
//  }
//}
