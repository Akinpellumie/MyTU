package com.akinpelumi.c2068220.mytu.ui.views.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.common.composables.BasicButton
import com.akinpelumi.c2068220.mytu.common.ext.basicButton
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import kotlinx.coroutines.delay
import com.akinpelumi.c2068220.mytu.R.string as AppText

private const val SPLASH_TIMEOUT = 1000L

@Composable
fun SplashScreen(
  openAndPopUp: (String, String) -> Unit,
  viewModel: SplashViewModel = hiltViewModel()
) {
  SplashScreenContent(
    onAppStart = { viewModel.onAppStart(openAndPopUp) },
    shouldShowError = viewModel.showError.value
  )
}

@Composable
fun SplashScreenContent(
  modifier: Modifier = Modifier,
  onAppStart: () -> Unit,
  shouldShowError: Boolean
) {
  Column(
    modifier =
    modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .background(color = MaterialTheme.customColorsPalette.white)
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if (shouldShowError) {
      Text(text = stringResource(AppText.generic_error))

      BasicButton(AppText.try_again, Modifier.basicButton()) { onAppStart() }
    } else {
      CircularProgressIndicator(color = MaterialTheme.customColorsPalette.primaryColor)
    }
  }

  LaunchedEffect(true) {
    delay(SPLASH_TIMEOUT)
    onAppStart()
  }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
  MyTUTheme {
    SplashScreenContent(
      onAppStart = { },
      shouldShowError = true
    )
  }
}
