package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinpelumi.c2068220.mytu.domain.model.Response.Loading
import com.akinpelumi.c2068220.mytu.domain.model.Response.Success
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.akinpelumi.c2068220.mytu.domain.repository.ReloadUserResponse
import com.akinpelumi.c2068220.mytu.domain.repository.RevokeAccessResponse
import com.akinpelumi.c2068220.mytu.ui.navigations.CALENDAR_WEBVIEW_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.MAIL_WEBVIEW_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Success(false))
        private set

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Loading
        reloadUserResponse = repo.reloadFirebaseUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    fun signOut() = viewModelScope.launch{
        repo.signOut()
    }

    fun onMailWebviewClick(navigateTo: (String) -> Unit) {
        navigateTo(MAIL_WEBVIEW_SCREEN)
    }
    fun onCalendarWebviewClick(navigateTo: (String) -> Unit) {
        navigateTo(CALENDAR_WEBVIEW_SCREEN)
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repo.revokeAccess()
    }
}
