package com.akinpelumi.c2068220.mytu.ui.views.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.ui.components.CustomAppBarPreview
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(modifier: Modifier = Modifier){
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
            CalendarContent()
        }
    }
}


@Composable
fun CalendarContent() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Text(text = "To Access your personalised calendar, click the button below",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.customColorsPalette.textColor,
        )
        }
        item {
            Spacer(
            Modifier.height(350.dp)
                .fillMaxWidth()
                .background(Color.Transparent)) }
        item {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    //goto outlook mail
                }) {
                Text(
                    text = "Go To MyCalendar",
                    color = MaterialTheme.customColorsPalette.white,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    MyTUTheme {
        CalendarScreen()
    }
}