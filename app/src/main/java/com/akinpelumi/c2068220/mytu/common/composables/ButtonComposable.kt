
package com.akinpelumi.c2068220.mytu.common.composables

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette

@Composable
fun BasicTextButton(text: String, modifier: Modifier, action: () -> Unit) {
  TextButton(onClick = action, modifier = modifier) { Text(text = text) }
}

@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
  Button(
    onClick = action,
    modifier = modifier,
    colors =
      ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.customColorsPalette.primaryColor,
        contentColor = MaterialTheme.customColorsPalette.white
      )
  ) {
    Text(text = stringResource(text), fontSize = 16.sp)
  }
}
