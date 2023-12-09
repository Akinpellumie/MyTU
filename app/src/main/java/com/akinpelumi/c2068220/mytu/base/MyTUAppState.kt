package com.akinpelumi.c2068220.mytu.base

import android.content.res.Resources
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.akinpelumi.c2068220.mytu.common.snackbar.SnackbarManager
import com.akinpelumi.c2068220.mytu.common.snackbar.SnackbarMessage.Companion.toMessage
import com.akinpelumi.c2068220.mytu.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
class MyTUAppState(
  //val scaffoldState: ScaffoldState,
  //private val context: ApplicationContext.
  val navController: NavHostController,
  private val snackbarManager: SnackbarManager,
  private val resources: Resources,
  coroutineScope: CoroutineScope
) {
  init {
    coroutineScope.launch {
      snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
        val text = snackbarMessage.toMessage(resources)
        //Toast.makeText(context, text,Toast.LENGTH_LONG).show()
        println(text)
//        showToast(context = , message = )
//        scaffoldState.snackbarHostState.showSnackbar(text)
//        snackbarManager.clearSnackbarState()
      }
    }
  }

  fun navigateBack() {
    navController.popBackStack()
  }

  fun navigate(route: String) {
    navController.navigate(route) { launchSingleTop = true }
  }

  fun navigateAndPopUp(route: String, popUp: String) {
    navController.navigate(route) {
      launchSingleTop = true
      popUpTo(popUp) { inclusive = true }
    }
  }

  fun clearAndNavigate(route: String) {
    navController.navigate(route) {
      launchSingleTop = true
      popUpTo(0) { inclusive = true }
    }
  }
}
