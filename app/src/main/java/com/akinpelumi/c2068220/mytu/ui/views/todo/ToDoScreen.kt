package com.akinpelumi.c2068220.mytu.ui.views.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
fun ToDoScreen( navigateBack: () -> Unit){
    Scaffold(
        topBar = {
            CustomToolbar(
                title = Constants.TODO_SCREEN,
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
            ToDoScreenContent()
        }
    }
}


@Composable
fun ToDoScreenContent() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item{
            Text(
                text = "Your To-Do List",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.customColorsPalette.textColor
            )
        }
        item{
            Text(
                text = "This page lists your tasks and reminders, including assessments due, requests to update your contact details and library loans. The list is updated regularly throughout the day",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.customColorsPalette.textColor
            )
        }
        item {
            Column (modifier = Modifier.padding(10.dp)){
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.customColorsPalette.itemBgColor,
                        shape = RectangleShape
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor,
                        shape = RoundedCornerShape(10.dp)
                    ),
                ){
                    Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_alert_up), // Replace with your logo resource
                            contentDescription = "alrt", // Set contentDescription to null for accessibility
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )
                        Text(
                            text = "Assessment  Due",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                        )
                        Card(
                            colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                            modifier = Modifier.align(alignment = Alignment.CenterVertically)

                        ){
                            Text(
                                text = "2",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.customColorsPalette.white,
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                                    .align(alignment = Alignment.CenterHorizontally)
                            )
                        }
                    }
                }


                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Transparent,
                        shape = RectangleShape
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor,
                        shape = RoundedCornerShape(10.dp)
                    ),
                ) {
                    Column {
                        var topExpanded by remember { mutableStateOf (false) }
                        var bottomExpanded by remember { mutableStateOf (false) }

                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable { topExpanded = !topExpanded }
                        ) {
                            Column(
                            ) {
                                // Card content goes here, which will be title and sample content
                                Text(
                                    text = "13 Dec 2023",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(8.dp)
                                )
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = "Mobile App Development - Blackboard",
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Spacer(
                                        Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .background(Color.Transparent))
                                    if (topExpanded){
                                        Icon(
                                            Icons.Filled.KeyboardArrowUp,
                                            tint = MaterialTheme.customColorsPalette.textColor,
                                            contentDescription = "arrow up",
                                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                        )
                                    }
                                    else{
                                        Icon(
                                            Icons.Filled.KeyboardArrowDown,
                                            tint = MaterialTheme.customColorsPalette.textColor,
                                            contentDescription = "arrow down",
                                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                                if (topExpanded) {
                                    Card(
                                        colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.itemBgColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .border(
                                                color = MaterialTheme.customColorsPalette.eventBorderColor,
                                                width = 1.dp, shape = RectangleShape
                                            )
                                            .padding(10.dp)

                                    ){
                                        Column {
                                            Text(
                                                text = "Mobile App Development - Blackboard",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                            )
                                            Text(
                                                text = "CIS4034-N - Mobile App Development is due on 13/12/2023. Submission method is Blackboard",
                                                style = MaterialTheme.typography.bodyMedium,
                                            )
                                            Text(
                                                text = "Due: 13-Dec-2023 16:00",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                            )
                                        }
                                    }

                                }
                            }
                        }
                        Divider( modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = MaterialTheme.customColorsPalette.borderColor,)

                        //bottom to-do expander
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable { bottomExpanded = !bottomExpanded }
                        ) {
                            Column(
                            ) {
                                // Card content goes here, which will be title and sample content
                                Text(
                                    text = "10 Jan 2024",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(8.dp)
                                )
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = "Artificial Intelligence\nFoundations - Blackboard",
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Spacer(
                                        Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .background(Color.Transparent))
                                    if (bottomExpanded){
                                        Icon(
                                            Icons.Filled.KeyboardArrowUp,
                                            tint = MaterialTheme.customColorsPalette.textColor,
                                            contentDescription = "arrow up",
                                            modifier = Modifier
                                                .align(alignment = Alignment.CenterVertically)
                                                .height(24.dp)
                                                .width(24.dp)
                                        )
                                    }
                                    else{
                                        Icon(
                                            Icons.Filled.KeyboardArrowDown,
                                            tint = MaterialTheme.customColorsPalette.textColor,
                                            contentDescription = "arrow down",
                                            modifier = Modifier
                                                .align(alignment = Alignment.CenterVertically)
                                                .height(24.dp)
                                                .width(24.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                                if (bottomExpanded) {
                                    Card(
                                        colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.itemBgColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .border(
                                                color = MaterialTheme.customColorsPalette.eventBorderColor,
                                                width = 1.dp, shape = RectangleShape
                                            )
                                            .padding(10.dp)

                                    ){
                                        Column {
                                            Text(
                                                text = "Artificial Intelligence Foundations - Blackboard",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                            )
                                            Text(
                                                text = "CIS4049-N - Artificial Intelligence Foundations is due on 10/01/2024. Submission method is Blackboard",
                                                style = MaterialTheme.typography.bodyMedium,
                                            )
                                            Text(
                                                text = "Due: 10-Jan-2024 16:00",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        item{
            Text(
                text = "Note: Tasks on your To-Do list should disappear within a few hours of being resolved within source system.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.customColorsPalette.textColor,
                modifier = Modifier.padding(start = 10.dp, top = 40.dp, end = 10.dp,)
            )
        }
        item{
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoScreenPreview() {
    MyTUTheme {
        ToDoScreen(navigateBack = {})
    }
}