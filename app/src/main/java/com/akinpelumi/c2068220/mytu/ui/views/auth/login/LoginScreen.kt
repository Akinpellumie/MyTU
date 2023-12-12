
package com.akinpelumi.c2068220.mytu.ui.views.auth.login

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.akinpelumi.c2068220.mytu.common.composables.*
import com.akinpelumi.c2068220.mytu.common.ext.Constants
import com.akinpelumi.c2068220.mytu.common.ext.Constants.FORGOT_PASSWORD
import com.akinpelumi.c2068220.mytu.common.ext.Constants.NO_ACCOUNT
import com.akinpelumi.c2068220.mytu.common.ext.Utils.Companion.showToastMessage
import com.akinpelumi.c2068220.mytu.common.ext.fieldModifier
import com.akinpelumi.c2068220.mytu.common.ext.textButton
import com.akinpelumi.c2068220.mytu.ui.components.CustomButton
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import com.akinpelumi.c2068220.mytu.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
  navigateToForgotPasswordScreen: () -> Unit,
  navigateToSignUpScreen: () -> Unit,
  viewModel: LoginViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState
  val context = LocalContext.current
  val activity = LocalContext.current as Activity

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
          onSignInWithMicrosoftClick = { viewModel.onSignInWithMicrosoftClick(activity) },
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
  onSignInWithMicrosoftClick: () -> Unit,
  onForgotPasswordClick: () -> Unit
) {
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

    BasicTextButton(FORGOT_PASSWORD, Modifier.textButton()) {
      onForgotPasswordClick()
    }

    CustomButton(
      title = Constants.SIGN_IN_BUTTON,
      onClick = {
        onSignInClick()
        if(!uiState.userIsAuthenticated){
          showToastMessage(context, uiState.error)
        }
                },
      modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    )



    BasicTextButton(NO_ACCOUNT, Modifier.textButton()) {
      onSignUpClick()
    }
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "OR",
      modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
      style = MaterialTheme.typography.headlineSmall,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.customColorsPalette.textColor,
    )
    OutlinedButton(
      onClick = { onSignInWithMicrosoftClick() },
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
        text = "Continue With Microsoft",
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
      onSignInWithMicrosoftClick = { },
      onForgotPasswordClick = { }
    )
  }
}
