package com.akinpelumi.c2068220.mytu.ui.views.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Library",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.customColorsPalette.textColor,
                    )
                },
                navigationIcon = {
                    // Add your logo here
                    Icon(
                        painter = painterResource(id = R.drawable.ic_left), // Replace with your logo resource
                        contentDescription = "logo", // Set contentDescription to null for accessibility
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.customColorsPalette.white)
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
            // Your content
            LibraryScreenContent()
        }
    }
}

@Composable
fun LibraryScreenContent(){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Column {
                Text(text = "Library Loans & Reservations",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Listed below are books that you have borrowed or reserved at the library.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.customColorsPalette.textColor,
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TimeTableScreenPreview() {
    MyTUTheme {
        LibraryScreen()
    }
}