package com.akinpelumi.c2068220.mytu.ui.views.mail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.showToast
import com.akinpelumi.c2068220.mytu.ui.components.CustomAppBarPreview
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MailScreen(modifier: Modifier = Modifier){
    Scaffold(
        topBar = { CustomAppBarPreview() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
            //.padding(it) // <<-- or simply this
        ) {
            // Your content
            MailContent()
        }
    }
}


@Composable
fun MailContent() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {  Text(text = "Teesside University provides all students with email facilities",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.customColorsPalette.textColor,
            )
        }
        item {  Text(text = "How do I access my email?",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.customColorsPalette.textColor,
            fontWeight = FontWeight.Bold,
            )
        }
        item {
            val context = LocalContext.current
            var showWebView by remember { mutableStateOf (false) }
            Row {
                Text(text = "Go to ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.customColorsPalette.textColor,
                )
                ClickableText(
                    text = AnnotatedString("https://outlook.office.com/") ,
                    style = TextStyle(color = MaterialTheme.customColorsPalette.linkTextColor, fontSize = TextUnit.Unspecified),
                    onClick = {
                        showWebView = true
                }
            )
                if(showWebView) {
                    showToast(context = context, message = "click to open outlook" )
                }

        }
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                        append("Use your student login with ")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified, fontWeight = FontWeight.Bold)) {
                        append("@live.tees.ac.uk")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                        append(" at the end, e.g. ")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified, fontWeight = FontWeight.Bold)) {
                        append("A1234567@live.tees.ac.uk")
                    }
                })
                Text(text = "What should you expect?",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Your email account is web based and can be accessed via any web browser or the Outlook App\n" +
                        "All university correspondence will go to your student email address.\n" +
                        "Your email address will close when you leave the University",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Normal
                )
                Text(text = "Information and Support",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "If you have questions or need help please email",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Normal
                )
            }

        }
    item {
        val context = LocalContext.current
        var openTeesHelp by remember { mutableStateOf (false) }
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ClickableText(
                text = AnnotatedString("ithelp@tees.ac.uk.") ,
                style = TextStyle(color = MaterialTheme.customColorsPalette.linkTextColor, fontWeight = FontWeight.Bold, fontSize = TextUnit.Unspecified),
                onClick = {
                    openTeesHelp = true
                }
            )

            ClickableText(
                text = AnnotatedString("Self Help guides") ,
                style = TextStyle(color = MaterialTheme.customColorsPalette.linkTextColor, fontWeight = FontWeight.Bold, fontSize = TextUnit.Unspecified),
                onClick = {
                    //openTeesHelp = true
                }
            )
            ClickableText(
                text = AnnotatedString("Connect your Mobile Phone") ,
                style = TextStyle(color = MaterialTheme.customColorsPalette.linkTextColor, fontWeight = FontWeight.Bold, fontSize = TextUnit.Unspecified),
                onClick = {
                    //openTeesHelp = true
                    //showToast(context = context, message = "opening tees.ac.uk website." )
                }
            )
        }

        if(openTeesHelp) {
            showToast(context = context, message = "opening tees.ac.uk website." )
        }
    }
        item {  Spacer(modifier = Modifier.height(20.dp))  }
        item {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                //goto outlook mail 
                }) {
                Text(
                    text = "Go To MyMail",
                    color = MaterialTheme.customColorsPalette.white,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

    @Preview(showBackground = true)
@Composable
fun MyMailPreview() {
    MyTUTheme {
        MailScreen()
    }
}