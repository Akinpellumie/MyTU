package com.akinpelumi.c2068220.mytu.ui.views.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.domain.model.Response.*
import com.akinpelumi.c2068220.mytu.ui.components.CustomProgressBar
import com.akinpelumi.c2068220.mytu.viewmodel.SignUpViewModel

@Composable
fun SendEmailVerification(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    when(val sendEmailVerificationResponse = viewModel.sendEmailVerificationResponse) {
        is Loading -> CustomProgressBar()
        is Success -> Unit
        is Failure -> sendEmailVerificationResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}