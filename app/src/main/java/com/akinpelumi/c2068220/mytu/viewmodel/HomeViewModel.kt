package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.lifecycle.ViewModel
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.akinpelumi.c2068220.mytu.ui.navigations.ATTENDANCE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.BALANCE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.LIBRARY_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.MODULE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.TIMETABLE_SCREEN
import com.akinpelumi.c2068220.mytu.ui.navigations.TODO_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: AuthRepository,
) : MyTUViewModel(repo) {
    val userDisplayName get() = repo.currentUser?.displayName
    val userEmail get() = repo.currentUser?.email
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