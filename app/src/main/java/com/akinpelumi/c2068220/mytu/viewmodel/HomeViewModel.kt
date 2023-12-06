package com.akinpelumi.c2068220.mytu.viewmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akinpelumi.c2068220.mytu.service.AccountService
import com.akinpelumi.c2068220.mytu.service.LogService
import com.akinpelumi.c2068220.mytu.ui.navigations.ATTENDANCE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.LOGIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.MAIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.SIGN_UP_SCREEN
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    fun onMyAttendanceClick(navigateTo: (String) -> Unit) {
        navigateTo(ATTENDANCE_SCREEN)
    }

}