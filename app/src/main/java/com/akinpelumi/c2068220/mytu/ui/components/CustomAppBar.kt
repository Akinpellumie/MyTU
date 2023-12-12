package com.akinpelumi.c2068220.mytu.ui.components

import android.annotation.SuppressLint
import androidx.annotation.ColorRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.common.ext.Utils.Companion.showToastMessage
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import com.akinpelumi.c2068220.mytu.viewmodel.LoginViewModel
import com.akinpelumi.c2068220.mytu.viewmodel.MainScreenViewModel

@Composable
fun CustomAppBar(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    CustomAppBarContent { viewModel.signOut() }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBarContent(
    signOutClick: () -> Unit
){
    val context = LocalContext.current
    TopAppBar(
        title = {},
        navigationIcon = {
            // Add your logo here
            Icon(
                painter = painterResource(id = R.drawable.ic_tu_logo), // Replace with your logo resource
                contentDescription = "logo", // Set contentDescription to null for accessibility
                tint = Color.Unspecified,
                modifier = Modifier
                    .width(150.dp)
                    .height(45.dp)
            )
        },
        actions = {
            // Add the logout icon to the right
            IconButton(
                onClick = {
                    signOutClick()
                    showToastMessage(context,"Current user logged out.")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout), // Replace with your logout icon resource
                    contentDescription = "Logout",
//                    modifier = Modifier.clickable {
//                        // Handle logout click
//                    }
                )
            }
        },
        colors =TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.customColorsPalette.white)
    )
}
@Preview(showBackground = true)
@Composable
fun CustomAppBarPreview() {
    CustomAppBar()
}