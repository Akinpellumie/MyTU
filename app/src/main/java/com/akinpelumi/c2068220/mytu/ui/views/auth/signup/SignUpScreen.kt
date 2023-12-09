
package com.akinpelumi.c2068220.mytu.ui.views.auth.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.common.composables.*
import com.akinpelumi.c2068220.mytu.common.ext.Constants.ALREADY_USER
import com.akinpelumi.c2068220.mytu.common.ext.Constants.SIGN_UP_BUTTON
import com.akinpelumi.c2068220.mytu.common.ext.Constants.SIGN_UP_SCREEN
import com.akinpelumi.c2068220.mytu.common.ext.Constants.VERIFY_EMAIL_MESSAGE
import com.akinpelumi.c2068220.mytu.common.ext.Utils.Companion.showToastMessage
import com.akinpelumi.c2068220.mytu.common.ext.fieldModifier
import com.akinpelumi.c2068220.mytu.ui.components.CustomButton
import com.akinpelumi.c2068220.mytu.ui.components.CustomToolbar
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.viewmodel.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
  //openAndPopUp: (String, String) -> Unit,
  navigateBack: () -> Unit,
  viewModel: SignUpViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState
  val context = LocalContext.current

  Scaffold(
    topBar = {
      CustomToolbar(
        title = SIGN_UP_SCREEN,
        navigateBack = navigateBack
      )
    },
    content = {
      Box(
        modifier = Modifier
        .fillMaxSize()
        .padding(
          top = it.calculateTopPadding(),
          bottom = it.calculateBottomPadding()
        )
      ) {
        SignUpScreenContent(
          uiState = uiState,
          onEmailChange = viewModel::onEmailChange,
          onPasswordChange = viewModel::onPasswordChange,
          onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
          onSignUpClick = { viewModel.onSignUpClick() },
          navigateBack = navigateBack
        )
      }
    }
  )

  SignUp(
    sendEmailVerification = {
      viewModel.sendEmailVerification()
    },
    showVerifyEmailMessage = {
      showToastMessage(context, VERIFY_EMAIL_MESSAGE)
    }
  )

  SendEmailVerification()
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreenContent(
  modifier: Modifier = Modifier,
  uiState: SignUpUiState,
  onEmailChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onRepeatPasswordChange: (String) -> Unit,
  onSignUpClick: () -> Unit,
  navigateBack: () -> Unit,
) {
  val fieldModifier = Modifier.fieldModifier()
  val keyboard = LocalSoftwareKeyboardController.current


  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    EmailField(uiState.email, onEmailChange, fieldModifier)
    PasswordField(uiState.password, onPasswordChange, fieldModifier)
    RepeatPasswordField(uiState.repeatPassword, onRepeatPasswordChange, fieldModifier)

//    BasicButton(AppText.create_account, Modifier.basicButton()) {
//      onSignUpClick()
//    }
    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(
      title = SIGN_UP_BUTTON,
      onClick = {
        keyboard?.hide()
        onSignUpClick()
      },
      modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    )
    Text(
      modifier = Modifier.clickable {
        navigateBack()
      },
      text = ALREADY_USER,
      fontSize = 15.sp
    )
  }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
  val uiState = SignUpUiState(
    email = "email@test.com"
  )

  MyTUTheme {
    SignUpScreenContent(
      uiState = uiState,
      onEmailChange = { },
      onPasswordChange = { },
      onRepeatPasswordChange = { },
      onSignUpClick = { },
      navigateBack = {}
    )
  }
}
