package com.akinpelumi.c2068220.mytu.ui.views.auth.verify_email.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.common.composables.BasicTextButton
import com.akinpelumi.c2068220.mytu.common.ext.Constants.ALREADY_VERIFIED
import com.akinpelumi.c2068220.mytu.common.ext.Constants.SPAM_EMAIL
import com.akinpelumi.c2068220.mytu.common.ext.textButton

@Composable
fun VerifyEmailContent(
    padding: PaddingValues,
    reloadUser: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding).padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextButton(ALREADY_VERIFIED, Modifier.textButton()) {
            reloadUser()
        }
//        Text(
//            modifier = Modifier.clickable {
//                reloadUser()
//            },
//            text = ALREADY_VERIFIED,
//            fontSize = 16.sp,
//            textDecoration = TextDecoration.Underline
//        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Text(
            text = SPAM_EMAIL,
            fontSize = 15.sp
        )
    }
}