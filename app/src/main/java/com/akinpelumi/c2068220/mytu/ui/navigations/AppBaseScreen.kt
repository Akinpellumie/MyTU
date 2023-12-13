package com.akinpelumi.c2068220.mytu.ui.navigations

sealed class AppBaseScreen(val route: String) {
    object SignInScreen: AppBaseScreen(LOGIN_SCREEN)
    object ForgotPasswordScreen: AppBaseScreen(FORGOT_PASSWORD_SCREEN)
    object SignUpScreen: AppBaseScreen(SIGN_UP_SCREEN)
    object VerifyEmailScreen: AppBaseScreen(VERIFY_EMAIL_SCREEN)
    object MainScreen: AppBaseScreen(MAIN_SCREEN)
    object ProfileScreen: AppBaseScreen(PROFILE_SCREEN)
    object EditProfileScreen: AppBaseScreen(EDIT_PROFILE_SCREEN)
    object AttendanceScreen: AppBaseScreen(ATTENDANCE_SCREEN)
    object TimetableScreen: AppBaseScreen(TIMETABLE_SCREEN)
    object ToDoScreen: AppBaseScreen(TODO_SCREEN)
    object BalanceScreen: AppBaseScreen(BALANCE_SCREEN)
    object LibraryScreen: AppBaseScreen(LIBRARY_SCREEN)
    object ModuleScreen: AppBaseScreen(MODULE_SCREEN)
    object CalendarWebViewScreen: AppBaseScreen(CALENDAR_WEBVIEW_SCREEN)
    object MailWebViewScreen: AppBaseScreen(MAIL_WEBVIEW_SCREEN)
}