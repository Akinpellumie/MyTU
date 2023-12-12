package com.akinpelumi.c2068220.mytu

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akinpelumi.c2068220.mytu.ui.navigations.AppBaseScreen
import com.akinpelumi.c2068220.mytu.ui.navigations.AppNavGraph
import com.akinpelumi.c2068220.mytu.viewmodel.MyTUViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyTUActivity : AppCompatActivity() {
  private lateinit var navController: NavHostController
  private val viewModel by viewModels<MyTUViewModel>()

  @OptIn(ExperimentalComposeUiApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      navController = rememberNavController()
      AppNavGraph(
        navController = navController
      )
      AuthState()
    }
    requestCameraPermission()
  }

  @Composable
  private fun AuthState() {
    val isUserSignedOut = viewModel.getAuthState()?.collectAsState()?.value
    if (isUserSignedOut == true || isUserSignedOut == null) {
      NavigateToSignInScreen()
    } else {
      if (viewModel.isEmailVerified) {
        NavigateToMainScreen()
      } else {
        NavigateToVerifyEmailScreen()
      }
    }
  }

  @Composable
  private fun NavigateToSignInScreen() = navController.navigate(AppBaseScreen.SignInScreen.route) {
    popUpTo(navController.graph.id) {
      inclusive = true
    }
  }

  @Composable
  private fun NavigateToMainScreen() = navController.navigate(AppBaseScreen.MainScreen.route) {
    popUpTo(navController.graph.id) {
      inclusive = true
    }
  }

  @Composable
  private fun NavigateToVerifyEmailScreen() = navController.navigate(AppBaseScreen.VerifyEmailScreen.route) {
    popUpTo(navController.graph.id) {
      inclusive = true
    }
  }
  private val requestPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) { isGranted ->
    if (isGranted) {
      println("myTUApp -->> Permission granted")
    } else {
      println("myTUApp -->> Permission denied")
    }
  }
  private fun requestCameraPermission() {
    when {
      ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.CAMERA
      ) == PackageManager.PERMISSION_GRANTED -> {
        println("myTUApp -->> Permission previously granted")
      }

      ActivityCompat.shouldShowRequestPermissionRationale(
        this,
        Manifest.permission.CAMERA
      ) ->  println("myTUApp -->> Show camera permissions dialog")

      else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }
  }
}
