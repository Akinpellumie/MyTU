package com.akinpelumi.c2068220.mytu.ui.views.attendance

data class AttendanceUiState(
    var isRegistered: Boolean = false,
    var inProgress: Boolean = false,
    var classCode: String = "",
    var error: String = ""
)
