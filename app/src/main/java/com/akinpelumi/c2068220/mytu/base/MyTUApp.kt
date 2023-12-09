package com.akinpelumi.c2068220.mytu.base

import android.Manifest
import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akinpelumi.c2068220.mytu.common.snackbar.SnackbarManager
import com.akinpelumi.c2068220.mytu.ui.navigations.LOGIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.MAIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.SIGN_UP_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.SPLASH_SCREEN
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import com.akinpelumi.c2068220.mytu.ui.views.base.MainScreen
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginScreen
import com.akinpelumi.c2068220.mytu.ui.views.auth.signup.SignUpScreen
//import com.akinpelumi.c2068220.mytu.ui.views.splash.SplashScreen
import com.akinpelumi.c2068220.mytu.common.composables.PermissionDialog
import com.akinpelumi.c2068220.mytu.common.composables.RationaleDialog
import com.akinpelumi.c2068220.mytu.ui.navigations.ALERT_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.ATTENDANCE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.CALENDAR_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.HOME_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.MAIL_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.PROFILE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.views.alert.AlertScreen
import com.akinpelumi.c2068220.mytu.ui.views.attendance.AttendanceScreen
import com.akinpelumi.c2068220.mytu.ui.views.calendar.CalendarScreen
import com.akinpelumi.c2068220.mytu.ui.views.home.HomeScreen
import com.akinpelumi.c2068220.mytu.ui.views.mail.MailScreen
import com.akinpelumi.c2068220.mytu.ui.views.profile.ProfileScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTUApp() {
  MyTUTheme {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      RequestPermissionDialog()
    }

    Surface(color = MaterialTheme.customColorsPalette.white) {
      val appState = rememberAppState()

      Scaffold(
      ) { innerPaddingModifier ->
        NavHost(
          navController = appState.navController,
          startDestination = LOGIN_SCREEN,
          modifier = Modifier.padding(innerPaddingModifier)
        ) {
          myTUGraph(appState)
        }
      }
    }
  }
}

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RequestPermissionDialog() {
  val permissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

  if (!permissionState.status.isGranted) {
    if (permissionState.status.shouldShowRationale) RationaleDialog()
    else PermissionDialog { permissionState.launchPermissionRequest() }
  }
}

@Composable
fun rememberAppState(
  //scaffoldState: ScaffoldState = rememberScaffoldState(),
  navController: NavHostController = rememberNavController(),
  snackbarManager: SnackbarManager = SnackbarManager,
  resources: Resources = resources(),
  coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
  remember(navController, snackbarManager, resources, coroutineScope) {
    MyTUAppState(navController, snackbarManager, resources, coroutineScope)
  }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
  LocalConfiguration.current
  return LocalContext.current.resources
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.myTUGraph(appState: MyTUAppState) {
  composable(SPLASH_SCREEN) {
    //SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
  }

//  composable(LOGIN_SCREEN) {
//    LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }, clearAndNavigate = { route -> appState.clearAndNavigate(route)})
//  }
//
//  composable(SIGN_UP_SCREEN) {
//    SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }, navigateBack = { appState.navigateBack() })
//  }

  composable(MAIN_SCREEN) { MainScreen() }
  composable(HOME_SCREEN) { HomeScreen(navigateTo={route-> appState.navigate(route) }) }
  composable(ALERT_SCREEN) { AlertScreen() }
  composable(MAIL_SCREEN) { MailScreen() }
  composable(ATTENDANCE_SCREEN) { AttendanceScreen() }
  composable(PROFILE_SCREEN) { ProfileScreen() }
  composable(CALENDAR_SCREEN) { CalendarScreen() }
}
