
package com.akinpelumi.c2068220.mytu.ui.views.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    var userIsAuthenticated: Boolean = false,
    var inProgress: Boolean = false,
    var error: String = ""
)
