package com.akinpelumi.c2068220.mytu.ui.views.auth.verify_email.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.akinpelumi.c2068220.mytu.common.ext.Constants.REVOKE_ACCESS_ITEM
import com.akinpelumi.c2068220.mytu.common.ext.Constants.SIGN_OUT_ITEM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyEmailTopBar(
    title: String,
    signOut: () -> Unit,
    revokeAccess: () -> Unit
) {
    var openMenu by remember { mutableStateOf(false) }

    TopAppBar (
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            openMenu = !openMenu
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = null,
                        )
                    }
                }
            }
        },
        actions = {
            DropdownMenu(
                expanded = openMenu,
                onDismissRequest = {
                    openMenu = !openMenu
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = SIGN_OUT_ITEM
                        )
                    },
                    onClick = {
                        signOut()
                        openMenu = !openMenu
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = REVOKE_ACCESS_ITEM
                        )
                    },
                    onClick = {
                        revokeAccess()
                        openMenu = !openMenu
                    }
                )
            }
        }
    )
}