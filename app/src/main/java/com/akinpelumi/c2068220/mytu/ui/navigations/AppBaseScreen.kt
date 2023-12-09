package com.akinpelumi.c2068220.mytu.ui.navigations

sealed class AppBaseScreen(val route: String) {
    object SignInScreen: AppBaseScreen(LOGIN_SCREEN)
    object ForgotPasswordScreen: AppBaseScreen(FORGOT_PASSWORD_SCREEN)
    object SignUpScreen: AppBaseScreen(SIGN_UP_SCREEN)
    object VerifyEmailScreen: AppBaseScreen(VERIFY_EMAIL_SCREEN)
    object MainScreen: AppBaseScreen(MAIN_SCREEN)
}