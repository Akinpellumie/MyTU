package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinpelumi.c2068220.mytu.common.ext.Constants.CLASS_CODE_ERROR_MESSAGE
import com.akinpelumi.c2068220.mytu.ui.views.attendance.AttendanceUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor() : ViewModel() {
    var uiState = mutableStateOf(AttendanceUiState())
        private set

    private val classCode
        get() = uiState.value.classCode

    fun onClassCodeChange(newValue: String) {
        uiState.value = uiState.value.copy(classCode = newValue)
    }

    fun onRegisterClickDelay() = viewModelScope.launch {
        delay(5000)
    }
    fun onRegisterClick() = viewModelScope.launch {
        // handle code reg here
        if (classCode.isNullOrBlank()) {
            uiState.value.isRegistered = false
            uiState.value.error = CLASS_CODE_ERROR_MESSAGE
            //return
        }
        else if (classCode.length > 4){
            uiState.value.isRegistered = false
            uiState.value.error = CLASS_CODE_ERROR_MESSAGE
            //return
        }
        else{
            //start class reg progress
            uiState.value.inProgress = true
            delay(3000)
            uiState.value.isRegistered = true
            uiState.value.inProgress = false
//            GlobalScope.launch {
//                delay(3_000L)
//
//            }

        }
    }
}