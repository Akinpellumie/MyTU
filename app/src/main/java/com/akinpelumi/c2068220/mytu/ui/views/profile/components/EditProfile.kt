package com.akinpelumi.c2068220.mytu.ui.views.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.ui.components.CustomProgressBar
import com.akinpelumi.c2068220.mytu.domain.model.Response.*
import com.akinpelumi.c2068220.mytu.viewmodel.ForgotPasswordViewModel
import com.akinpelumi.c2068220.mytu.viewmodel.ProfileViewModel

@Composable
fun EditProfile(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    showEditProfileMessage: () -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val editProfileResponse = viewModel.editProfileResponse) {
        is Loading -> CustomProgressBar()
        is Success -> {
            val isProfileInfoUpdated = editProfileResponse.data
            LaunchedEffect(isProfileInfoUpdated) {
                if (isProfileInfoUpdated) {
                    navigateBack()
                    showEditProfileMessage()
                }
            }
        }
        is Failure -> editProfileResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
        }
    }
}