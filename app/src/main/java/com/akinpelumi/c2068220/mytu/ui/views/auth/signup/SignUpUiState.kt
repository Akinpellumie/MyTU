
package com.akinpelumi.c2068220.mytu.ui.views.auth.signup

data class SignUpUiState(
  val email: String = "",
  val password: String = "",
  val repeatPassword: String = "",
  var isValidCredentials: Boolean = true,
  var errorMsg: String = ""
)
