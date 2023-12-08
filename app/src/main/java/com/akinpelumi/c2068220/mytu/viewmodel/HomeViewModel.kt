package com.akinpelumi.c2068220.mytu.viewmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akinpelumi.c2068220.mytu.service.AccountService
import com.akinpelumi.c2068220.mytu.service.LogService
import com.akinpelumi.c2068220.mytu.ui.navigations.ATTENDANCE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.BALANCE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.LIBRARY_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.LOGIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.MAIN_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.MODULE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.SIGN_UP_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.TIMETABLE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.TODO_SCREEN
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    fun onMyAttendanceClick(navigateTo: (String) -> Unit) {
        navigateTo(ATTENDANCE_SCREEN)
    }
    fun onTimetableClick(navigateTo: (String) -> Unit) {
        navigateTo(TIMETABLE_SCREEN)
    }

    fun onModuleClick(navigateTo: (String) -> Unit) {
        navigateTo(MODULE_SCREEN)
    }
    fun onBalanceClick(navigateTo: (String) -> Unit) {
        navigateTo(BALANCE_SCREEN)
    }
    fun onLibraryClick(navigateTo: (String) -> Unit) {
        navigateTo(LIBRARY_SCREEN)
    }
    fun onToDoClick(navigateTo: (String) -> Unit) {
        navigateTo(TODO_SCREEN)
    }

}