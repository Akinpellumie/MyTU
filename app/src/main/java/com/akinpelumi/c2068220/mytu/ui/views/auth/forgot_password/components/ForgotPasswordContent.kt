package com.akinpelumi.c2068220.mytu.ui.views.auth.forgot_password.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.common.composables.EmailField
import com.akinpelumi.c2068220.mytu.common.ext.Constants.RESET_PASSWORD_BUTTON
import com.akinpelumi.c2068220.mytu.common.ext.fieldModifier
import com.akinpelumi.c2068220.mytu.ui.components.CustomButton
import com.akinpelumi.c2068220.mytu.ui.views.auth.forgot_password.ForgotPasswordUiState

@Composable
fun ForgotPasswordContent(
    padding: PaddingValues,
    onEmailChange: (String) -> Unit,
    uiState: ForgotPasswordUiState,
    sendPasswordResetEmail: () -> Unit,
) {
    val fieldModifier = Modifier.fieldModifier()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmailField(uiState.email, onEmailChange, fieldModifier)
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        CustomButton(
            title = RESET_PASSWORD_BUTTON,
            onClick = { sendPasswordResetEmail() },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )
    }
}