
package com.akinpelumi.c2068220.mytu.ui.views.profile

import android.net.Uri

data class ProfileUiState(
    val fullName: String = "",
    val imageUri: Uri = Uri.EMPTY,
    var isSuccess: Boolean = false,
    var inProgress: Boolean = false,
    var error: String = ""
)
