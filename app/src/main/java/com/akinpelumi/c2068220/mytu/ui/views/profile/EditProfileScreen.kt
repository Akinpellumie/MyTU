package com.akinpelumi.c2068220.mytu.ui.views.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.common.ext.Constants
import com.akinpelumi.c2068220.mytu.common.ext.Utils
import com.akinpelumi.c2068220.mytu.ui.components.CustomToolbar
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.views.profile.components.EditProfile
import com.akinpelumi.c2068220.mytu.ui.views.profile.components.EditProfileScreenContent
import com.akinpelumi.c2068220.mytu.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen (
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateBack: () -> Unit
){
    val uiState by viewModel.uiState
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CustomToolbar(
                title = Constants.EDIT_INFO_SCREEN,
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            EditProfileScreenContent(
                padding = padding,
                viewModel::onDisplayNameChange,
                viewModel::onDisplayImageChange,
                uiState = uiState,
                updateProfileInfo = {
                    viewModel.onUpdateInfoClick()
                }
            )
        }
    )
    EditProfile(
        navigateBack = navigateBack,
        showEditProfileMessage = {
            Utils.showToastMessage(context, Constants.EDIT_INFO_MESSAGE)
        },
        showErrorMessage = { errorMessage ->
            Utils.showToastMessage(context, errorMessage)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    MyTUTheme {
        EditProfileScreen(navigateBack = {})
    }
}