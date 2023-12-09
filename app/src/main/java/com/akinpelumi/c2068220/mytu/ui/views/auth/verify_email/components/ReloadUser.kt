package com.akinpelumi.c2068220.mytu.ui.views.auth.verify_email.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.domain.model.Response.Failure
import com.akinpelumi.c2068220.mytu.domain.model.Response.Loading
import com.akinpelumi.c2068220.mytu.domain.model.Response.Success
import com.akinpelumi.c2068220.mytu.ui.components.CustomProgressBar
import com.akinpelumi.c2068220.mytu.viewmodel.MainScreenViewModel

@Composable
fun ReloadUser(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    when(val reloadUserResponse = viewModel.reloadUserResponse) {
        is Loading -> CustomProgressBar()
        is Success -> {
            val isUserReloaded = reloadUserResponse.data
            LaunchedEffect(isUserReloaded) {
                if (isUserReloaded) {
                    navigateToProfileScreen()
                }
            }
        }
        is Failure -> reloadUserResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}