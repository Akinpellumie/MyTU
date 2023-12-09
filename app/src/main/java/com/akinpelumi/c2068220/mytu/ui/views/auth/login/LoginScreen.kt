
package com.akinpelumi.c2068220.mytu.ui.views.auth.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.viewmodel.LoginViewModel
import com.akinpelumi.c2068220.mytu.R.string as AppText
import com.akinpelumi.c2068220.mytu.common.composables.*
import com.akinpelumi.c2068220.mytu.common.ext.Utils.Companion.showToastMessage
import com.akinpelumi.c2068220.mytu.common.ext.basicButton
import com.akinpelumi.c2068220.mytu.common.ext.fieldModifier
import com.akinpelumi.c2068220.mytu.common.ext.textButton
import com.akinpelumi.c2068220.mytu.showToast
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
//  openAndPopUp: (String, String) -> Unit,
//  clearAndNavigate: (String) -> Unit,
  navigateToForgotPasswordScreen: () -> Unit,
  navigateToSignUpScreen: () -> Unit,
  viewModel: LoginViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState
  val context = LocalContext.current

  Scaffold(
    content = {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(
            top = it.calculateTopPadding(),
            bottom = it.calculateBottomPadding()
          )
      ) {
        LoginScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSignInClick() },
        onSignUpClick = navigateToSignUpScreen,
        onForgotPasswordClick = navigateToForgotPasswordScreen
      )
      }
    }
  )

  SignIn(
    showErrorMessage = { errorMessage ->
      showToastMessage(context, errorMessage)
    }
  )
}

@Composable
fun LoginScreenContent(
  modifier: Modifier = Modifier,
  uiState: LoginUiState,
  onEmailChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onSignInClick: () -> Unit,
  onSignUpClick: () -> Unit,
  onForgotPasswordClick: () -> Unit
) {
//  BasicToolbar(AppText.login_details)
val context = LocalContext.current
  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    EmailField(uiState.email, onEmailChange, Modifier.fieldModifier())
    PasswordField(uiState.password, onPasswordChange, Modifier.fieldModifier())

    BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
      onForgotPasswordClick()
    }
    BasicButton(AppText.sign_in, Modifier.basicButton()) {
      onSignInClick()
      if(!uiState.userIsAuthenticated){
        Toast.makeText(context, uiState.error,Toast.LENGTH_SHORT).show()
      //showToast(context = context, message = uiState.error)
    } }



    BasicTextButton(AppText.signup, Modifier.textButton()) {
      onSignUpClick()
    }
    Spacer(modifier = Modifier.height(30.dp))
    Text(text = "OR",
      modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
      style = MaterialTheme.typography.headlineMedium,
      color = MaterialTheme.customColorsPalette.textColor,
      //modifier = Modifier.padding(vertical = 5.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))

//    Row {
//      Text(text = "Continue With",
//        style = MaterialTheme.typography.bodyMedium,
//        color = MaterialTheme.customColorsPalette.textColor,
//        modifier = Modifier
//          .padding(horizontal = 5.dp)
//          .align(alignment = Alignment.CenterVertically)
//      )
//      IconButton(onClick = { /* do something */ }) {
//        Icon(
//          painter = painterResource(id = R.drawable.ic_google), // Replace with your logo resource
//          contentDescription = "google", // Set contentDescription to null for accessibility
//          tint = Color.Unspecified,
//          modifier = Modifier
//            .width(50.dp)
//            .height(50.dp)
//        )
//      }
//    }
    OutlinedButton(
      onClick = { },
      modifier = Modifier.fillMaxWidth().padding(20.dp),
      border = BorderStroke(1.dp, MaterialTheme.customColorsPalette.accentColor),
      shape = RoundedCornerShape(30.dp),
      colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.customColorsPalette.accentColor)
    ){
      Icon(
        painter = painterResource(id = R.drawable.ic_google),
        contentDescription = "google",
        tint = Color.Unspecified,
        modifier = Modifier
          .width(40.dp)
          .height(40.dp)
      )
      Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
      Text(
        text = "Continue With Google",
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
          .weight(1f)
          .offset(x = 20.dp),
        color = MaterialTheme.customColorsPalette.accentColor
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
  val uiState = LoginUiState(
    email = "email@test.com"
  )
  MyTUTheme {
    LoginScreenContent(
      uiState = uiState,
      onEmailChange = { },
      onPasswordChange = { },
      onSignInClick = { },
      onSignUpClick = { },
      onForgotPasswordClick = { }
    )
  }
}
