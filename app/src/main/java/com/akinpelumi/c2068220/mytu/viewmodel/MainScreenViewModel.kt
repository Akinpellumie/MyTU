package com.akinpelumi.c2068220.mytu.viewmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akinpelumi.c2068220.mytu.service.AccountService
import com.akinpelumi.c2068220.mytu.service.LogService
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
}