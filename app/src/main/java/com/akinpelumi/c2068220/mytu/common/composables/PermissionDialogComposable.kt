package com.akinpelumi.c2068220.mytu.common.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.akinpelumi.c2068220.mytu.common.ext.alertDialog
import com.akinpelumi.c2068220.mytu.common.ext.textButton
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import com.akinpelumi.c2068220.mytu.R.string as AppText

@Composable
fun PermissionDialog(onRequestPermission: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(true) }

    if (showWarningDialog) {
        AlertDialog(
            modifier = Modifier.alertDialog(),
            title = { Text(stringResource(id = AppText.location_permission_title)) },
            text = { Text(stringResource(id = AppText.location_permission_description)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onRequestPermission()
                        showWarningDialog = false
                    },
                    modifier = Modifier.textButton(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.customColorsPalette.primaryColor,
                        contentColor = Color.White
                    )
                ) { Text(text = stringResource(AppText.request_location_permission)) }
            },
            onDismissRequest = { }
        )
    }
}

@Composable
fun RationaleDialog() {
    var showWarningDialog by remember { mutableStateOf(true) }

    if (showWarningDialog) {
        AlertDialog(
            modifier = Modifier.alertDialog(),
            title = { Text(stringResource(id = AppText.location_permission_title)) },
            text = { Text(stringResource(id = AppText.location_permission_settings)) },
            confirmButton = {
                TextButton(
                    onClick = { showWarningDialog = false },
                    modifier = Modifier.textButton(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.customColorsPalette.primaryColor,
                        contentColor = Color.White
                    )
                ) { Text(text = stringResource(AppText.ok)) }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}