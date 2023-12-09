package com.akinpelumi.c2068220.mytu.ui.views.base

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.common.ext.Constants.ACCESS_REVOKED_MESSAGE
import com.akinpelumi.c2068220.mytu.common.ext.Constants.REVOKE_ACCESS_MESSAGE
import com.akinpelumi.c2068220.mytu.common.ext.Constants.SENSITIVE_OPERATION_MESSAGE
import com.akinpelumi.c2068220.mytu.common.ext.Constants.SIGN_OUT_ITEM
import com.akinpelumi.c2068220.mytu.common.ext.Utils.Companion.showToastMessage
import com.akinpelumi.c2068220.mytu.domain.model.Response.Failure
import com.akinpelumi.c2068220.mytu.domain.model.Response.Loading
import com.akinpelumi.c2068220.mytu.domain.model.Response.Success
import com.akinpelumi.c2068220.mytu.ui.components.CustomProgressBar
import com.akinpelumi.c2068220.mytu.viewmodel.MainScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RevokeAccess(
    viewModel: MainScreenViewModel = hiltViewModel(),
    scaffoldState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = scaffoldState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT_ITEM
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
        is Loading -> CustomProgressBar()
        is Success -> {
            val isAccessRevoked = revokeAccessResponse.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked) {
                    showToastMessage(context, ACCESS_REVOKED_MESSAGE)
                }
            }
        }
        is Failure -> revokeAccessResponse.apply {
            LaunchedEffect(e) {
                print(e)
                if (e.message == SENSITIVE_OPERATION_MESSAGE) {
                    showRevokeAccessMessage()
                }
            }
        }
    }
}