package com.akinpelumi.c2068220.mytu.ui.views.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.common.composables.BasicFieldWithSupportText
import com.akinpelumi.c2068220.mytu.common.ext.Constants
import com.akinpelumi.c2068220.mytu.common.ext.Constants.CLASS_CODE
import com.akinpelumi.c2068220.mytu.ui.components.CustomToolbar
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import com.akinpelumi.c2068220.mytu.ui.views.auth.login.LoginUiState
import com.akinpelumi.c2068220.mytu.viewmodel.AttendanceViewModel
import com.akinpelumi.c2068220.mytu.viewmodel.LoginViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(
    navigateBack: () -> Unit,
    viewModel: AttendanceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    Scaffold(
        topBar = {
            CustomToolbar(
                title = Constants.ATTENDANCE_SCREEN,
                navigateBack = navigateBack
            )
        }
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
            // screen content
            AttendanceScreenContent(
                onClassCodeChange = viewModel::onClassCodeChange,
                uiState = uiState,
                onRegisterClick = { viewModel.onRegisterClickDelay() }
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreenContent(
    onClassCodeChange: (String) -> Unit,
    uiState: AttendanceUiState,
    onRegisterClick: () -> Unit
    ) {
    val maxChar = 4
    var isRegistered by remember { mutableStateOf (false) }
    var inProgress by remember { mutableStateOf (false) }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)){
        item {  Text(text = "Register Class Attendance",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.customColorsPalette.textColor,
            fontWeight = FontWeight.Bold,
        )
        }
        item {
            Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                append("Your timetabled classes are displayed below. To record your attendance, enter a ")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified, fontWeight = FontWeight.Bold)) {
                append("Verification Code")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                append(" and click the ")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified, fontWeight = FontWeight.Bold)) {
                append("Register")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                append(" button next to the class that you want to register for. If successful, you will see a ")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified, fontWeight = FontWeight.Bold)) {
                append("'Registered'")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                append(" confirmation message.")
            }
        }) }
        item {
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                        append("\u2022")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                        append(" Verification codes will be supplied by your  lecturer.")
                    }
                })
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                        append("\u2022")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                        append(" Your verification code can be entered 30 minutes before the start of an event and up to 3 hours after the end of an event.")
                    }
                })
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                        append("\u2022")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.customColorsPalette.textColor, letterSpacing= TextUnit.Unspecified)) {
                        append(" You have a maximum of 10 attempts to register for each class.")
                    }
                })
            }
        }
        item { Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Box{
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_check), // Replace with your logo resource
                    contentDescription = "logo", // Set contentDescription to null for accessibility
                    tint = MaterialTheme.customColorsPalette.textColor,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Transparent,
                    shape = RectangleShape
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.customColorsPalette.textColor,
                    shape = RoundedCornerShape(10.dp)
                ),
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Mobile App Development",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Row{
                        Text(
                            text = "Session: ",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Lecture",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Row{
                        Text(
                            text = "Time: ",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "15:00 - 16:00 (05 Dec 2023)",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Text(
                        text = "Registration period not yet open (14:30 - 19:00) ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.customColorsPalette.redVariantColor,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        } }
        item { Spacer(modifier = Modifier.height(10.dp)) }

        item { Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Box{
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_check), // Replace with your logo resource
                    contentDescription = "logo", // Set contentDescription to null for accessibility
                    tint = MaterialTheme.customColorsPalette.textColor,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Transparent,
                    shape = RectangleShape
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.customColorsPalette.textColor,
                    shape = RoundedCornerShape(10.dp)
                ),
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Mobile App Development",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Row{
                        Text(
                            text = "Session: ",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Lecture",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Row{
                        Text(
                            text = "Time: ",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "15:00 - 16:00 (05 Dec 2023)",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Column {
                        if(isRegistered){
                            Row {
                                Text(
                                    text = "Registered ",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.customColorsPalette.greenColor
                                )
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    modifier = Modifier.height(20.dp).width(20.dp),
                                    tint = MaterialTheme.customColorsPalette.greenColor
                                )
                            }
                        }
                        else{
                            Row {
                                BasicFieldWithSupportText(
                                    CLASS_CODE,
                                    maxChar,
                                    uiState.classCode,
                                    onClassCodeChange,
                                    Modifier
                                        .width(200.dp)
                                        .padding(16.dp, 4.dp),
                                )
                                if (inProgress) {
                                    //show progress bar
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                    if(!isRegistered){
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.customColorsPalette.accentColor),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .width(230.dp)
                                .height(40.dp),
                            onClick = {
                                //handle class code registration
                                inProgress = !inProgress
                                onRegisterClick()
                                isRegistered = !isRegistered
                                inProgress = !inProgress
                            }) {
                            Text(
                                text = "Register",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.customColorsPalette.white,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }

                }
            }
        } }

        item{
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AttendanceScreenPreview() {
    MyTUTheme {
        AttendanceScreen(navigateBack = {})
    }
}