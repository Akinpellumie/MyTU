/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.akinpelumi.c2068220.mytu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akinpelumi.c2068220.mytu.base.MyTUApp
import com.akinpelumi.c2068220.mytu.ui.navigations.AppBaseScreen
import com.akinpelumi.c2068220.mytu.ui.navigations.AppNavGraph
import com.akinpelumi.c2068220.mytu.viewmodel.MyTUViewModel
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
//class MyTUActivity : AppCompatActivity() {
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//
//    setContent { MyTUApp() }
//  }
//}
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
}
