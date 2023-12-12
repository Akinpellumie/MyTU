package com.akinpelumi.c2068220.mytu.ui.views.modules

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
import com.akinpelumi.c2068220.mytu.common.ext.Constants
import com.akinpelumi.c2068220.mytu.ui.components.CustomToolbar
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuleScreen(navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = Constants.MODULE_SCREEN,
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
            // Your content
            ModuleScreenContent()
        }
    }
}

@Composable
fun ModuleScreenContent(){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Column {
                Text(text = "Your Modules",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Listed below are the modules that you are currently enroled on, plus details of any module feedback due. You may also view module specifications and link to learning materials in Blackboard.",
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
        ModuleScreen(navigateBack = {})
    }
}