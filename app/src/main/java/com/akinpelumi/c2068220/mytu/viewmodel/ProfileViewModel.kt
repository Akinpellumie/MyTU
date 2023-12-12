package com.akinpelumi.c2068220.mytu.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.akinpelumi.c2068220.mytu.domain.model.Response
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.akinpelumi.c2068220.mytu.domain.repository.UpdateUserResponse
import com.akinpelumi.c2068220.mytu.ui.navigations.EDIT_PROFILE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.views.profile.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AuthRepository,
) : MyTUViewModel(repo) {
    var uiState = mutableStateOf(ProfileUiState())
        private set
    var editProfileResponse by mutableStateOf<UpdateUserResponse>(Response.Success(false))
        private set

    private val fullName
        get() = uiState.value.fullName

    private val uploadedImageUri
        get() = uiState.value.imageUri

    val userDisplayName get() = repo.currentUser?.displayName
    val profileImageUrl get() = repo.currentUser?.photoUrl
    val userEmail get() = repo.currentUser?.email

    fun onEditProfileClick(navigateTo: (String) -> Unit) {
        navigateTo(EDIT_PROFILE_SCREEN)
    }
    fun onDisplayNameChange(newValue: String) {
        uiState.value = uiState.value.copy(fullName = newValue)
    }
    fun onDisplayImageChange(newValue: Uri) {
        uiState.value = uiState.value.copy(imageUri = newValue)
    }

    fun onUpdateInfoClick() {
        if (fullName.isNullOrEmpty()) {
            uiState.value.isSuccess = false
            uiState.value.error = "Please enter a display name"
            return
        }
        //start auth progress
        uiState.value.inProgress = true
        launchCatching {
            editProfileResponse = Response.Loading
            editProfileResponse = repo.firebaseUpdateUserProfile(fullName, uploadedImageUri)
        }
    }

}