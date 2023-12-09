package com.akinpelumi.c2068220.mytu.ui.views.auth.forgot_password

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.common.ext.Constants
import com.akinpelumi.c2068220.mytu.common.ext.Constants.RESET_PASSWORD_MESSAGE
import com.akinpelumi.c2068220.mytu.common.ext.Utils.Companion.showToastMessage
import com.akinpelumi.c2068220.mytu.ui.components.CustomToolbar
import com.akinpelumi.c2068220.mytu.ui.views.auth.forgot_password.components.ForgotPassword
import com.akinpelumi.c2068220.mytu.ui.views.auth.forgot_password.components.ForgotPasswordContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val uiState by viewModel.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CustomToolbar(
                title = Constants.FORGOT_PASSWORD_SCREEN,
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            ForgotPasswordContent(
                padding = padding,
                viewModel::onEmailChange,
                uiState = uiState,
                sendPasswordResetEmail = {
                    viewModel.sendPasswordResetEmail()
                }
            )
        }
    )

    ForgotPassword(
        navigateBack = navigateBack,
        showResetPasswordMessage = {
            showToastMessage(context, RESET_PASSWORD_MESSAGE)
        },
        showErrorMessage = { errorMessage ->
            showToastMessage(context, errorMessage)
        }
    )
}