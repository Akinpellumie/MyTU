package com.akinpelumi.c2068220.mytu.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akinpelumi.c2068220.mytu.ui.views.attendance.AttendanceScreen
import com.akinpelumi.c2068220.mytu.ui.views.auth.forgot_password.ForgotPasswordScreen
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginScreen
import com.akinpelumi.c2068220.mytu.ui.views.auth.signup.SignUpScreen
import com.akinpelumi.c2068220.mytu.ui.views.auth.verify_email.VerifyEmailScreen
import com.akinpelumi.c2068220.mytu.ui.views.balances.BalanceScreen
import com.akinpelumi.c2068220.mytu.ui.views.base.MainScreen
import com.akinpelumi.c2068220.mytu.ui.views.library.LibraryScreen
import com.akinpelumi.c2068220.mytu.ui.views.modules.ModuleScreen
import com.akinpelumi.c2068220.mytu.ui.views.profile.EditProfileScreen
import com.akinpelumi.c2068220.mytu.ui.views.timetable.TimeTableScreen
import com.akinpelumi.c2068220.mytu.ui.views.todo.ToDoScreen

@Composable
@ExperimentalComposeUiApi
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppBaseScreen.SignInScreen.route
    ) {
        composable(
            route = AppBaseScreen.SignInScreen.route
        ) {
            //LoginScreen(openAndPopUp = { route, popUp -> navController.navigate(route) }, clearAndNavigate = { route -> appState.clearAndNavigate(route)})

            LoginScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(AppBaseScreen.ForgotPasswordScreen.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(AppBaseScreen.SignUpScreen.route)
                }
            )
        }
        composable(
            route = AppBaseScreen.ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.SignUpScreen.route
        ) {
            SignUpScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.VerifyEmailScreen.route
        ) {
            VerifyEmailScreen(
                navigateToProfileScreen = {
                    navController.navigate(AppBaseScreen.MainScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = AppBaseScreen.MainScreen.route
        ) {
            MainScreen()
        }
        composable(
            route = AppBaseScreen.EditProfileScreen.route
        ) {
            EditProfileScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.AttendanceScreen.route
        ) {
            AttendanceScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.TimetableScreen.route
        ) {
            TimeTableScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.ToDoScreen.route
        ) {
            ToDoScreen (
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.BalanceScreen.route
        ) {
            BalanceScreen (
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.LibraryScreen.route
        ) {
            LibraryScreen (
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.ModuleScreen.route
        ) {
            ModuleScreen (
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}