package com.akinpelumi.c2068220.mytu.ui.views.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.domain.model.Response.*
import com.akinpelumi.c2068220.mytu.ui.components.CustomProgressBar
import com.akinpelumi.c2068220.mytu.viewmodel.LoginViewModel

@Composable
fun SignIn(
    viewModel: LoginViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val signInResponse = viewModel.signInResponse) {
        is Loading -> CustomProgressBar()
        is Success -> Unit
        is Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
        }
    }
}