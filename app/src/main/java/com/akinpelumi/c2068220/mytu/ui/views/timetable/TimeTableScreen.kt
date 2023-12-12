package com.akinpelumi.c2068220.mytu.ui.views.timetable

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.common.ext.Constants
import com.akinpelumi.c2068220.mytu.ui.components.CustomToolbar
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTableScreen(
    navigateBack: () -> Unit
){
    Scaffold(
        topBar = {
            CustomToolbar(
                title = Constants.TIMETABLE_SCREEN,
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
            TimeTableScreenContent()
        }
    }
}


@Composable
fun TimeTableScreenContent() {
    //day-expander section
    var monExpanded by remember { mutableStateOf (false) }
    var tueExpanded by remember { mutableStateOf (false) }
    var wedExpanded by remember { mutableStateOf (false) }
    var thurExpanded by remember { mutableStateOf (false) }
    var friExpanded by remember { mutableStateOf (false) }
    var satExpanded by remember { mutableStateOf (false) }
    var sunExpanded by remember { mutableStateOf (false) }

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("03-Dec-2023") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth-${mMonth+1}-$mYear"
        }, mYear, mMonth, mDay
    )
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Column {
                Text(text = "Timetable",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Select a date below to display your timetable for the chosen week. Please note that timetables can change so check this page regularly.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.customColorsPalette.textColor,
                )
            }
        }

        item {
            Row{
                Box (
                    modifier = Modifier
                        .height(40.dp)
                        .background(
                            color = MaterialTheme.customColorsPalette.itemBgColor,
                            shape = RectangleShape
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.customColorsPalette.borderColor,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    contentAlignment = Alignment.CenterStart,
                ){
                    Column(modifier = Modifier.wrapContentSize(Alignment.Center)) {
                        Text(
                            text = "Date",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }

                //datepicker
                Box (
                    modifier = Modifier
                        .clickable {
                            mDatePickerDialog.show()
                        }
                        .height(40.dp)
                        .padding(end = 20.dp)
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
                    contentAlignment = Alignment.CenterStart,
                ){
                    Text(
                        text = "${mDate.value}",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

            }
        }

        // show datepicker alt button
        item{
            OutlinedButton(
                onClick = { },
                border = BorderStroke(1.dp, MaterialTheme.customColorsPalette.linkTextColor),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.customColorsPalette.linkTextColor)
            ){
                Text(text = "Show Timetable",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.customColorsPalette.linkTextColor
                )
            }
        }
        item{
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.customColorsPalette.itemBgColor,
                        shape = RectangleShape
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor,
                        shape = RoundedCornerShape(5.dp)
                    )
            ){
                Column {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .clickable { monExpanded = !monExpanded }
                    ) {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        text = "Monday, 04 Dec 2023",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                                    )
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterVertically)
                                            .padding(start = 5.dp)

                                    ) {
                                        Text(
                                            text = "0",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.customColorsPalette.white,
                                            modifier = Modifier
                                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                                .align(alignment = Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                                Spacer(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Transparent)
                                )
                                if (monExpanded) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.KeyboardArrowDown,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow down",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            if (monExpanded) {
                                Text(
                                    text = "No events on Monday, 04 Dec 2023",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.customColorsPalette.textColor,
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor
                    )
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .clickable { tueExpanded = !tueExpanded }
                    ) {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        text = "Tuesday, 05 Dec 2023",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterVertically)
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)

                                    ) {
                                        Text(
                                            text = "0",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.customColorsPalette.white,
                                            modifier = Modifier
                                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                                .align(alignment = Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                                Spacer(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Transparent)
                                )
                                if (tueExpanded) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.KeyboardArrowDown,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow down",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            if (tueExpanded) {
                                Text(
                                    text = "No events on Tuesday, 05 Dec 2023",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.customColorsPalette.textColor,
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor
                    )
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .clickable { wedExpanded = !wedExpanded }
                    ) {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        text = "Wednesday, 06 Dec 2023",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterVertically)
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)

                                    ) {
                                        Text(
                                            text = "0",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.customColorsPalette.white,
                                            modifier = Modifier
                                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                                .align(alignment = Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                                Spacer(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Transparent)
                                )
                                if (wedExpanded) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.KeyboardArrowDown,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow down",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            if (wedExpanded) {
                                Text(
                                    text = "No events on Wednesday, 06 Dec 2023",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.customColorsPalette.textColor,
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor
                    )
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .clickable { thurExpanded = !thurExpanded }
                    ) {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        text = "Thursday, 07 Dec 2023",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterVertically)
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)

                                    ) {
                                        Text(
                                            text = "0",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.customColorsPalette.white,
                                            modifier = Modifier
                                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                                .align(alignment = Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                                Spacer(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Transparent)
                                )
                                if (thurExpanded) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.KeyboardArrowDown,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow down",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            if (thurExpanded) {
                                Text(
                                    text = "No events on Thursday, 07 Dec 2023",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.customColorsPalette.textColor,
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor
                    )
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .clickable { friExpanded = !friExpanded }
                    ) {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        text = "Friday, 08 Dec 2023",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterVertically)
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)

                                    ) {
                                        Text(
                                            text = "0",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.customColorsPalette.white,
                                            modifier = Modifier
                                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                                .align(alignment = Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                                Spacer(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Transparent)
                                )
                                if (friExpanded) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.KeyboardArrowDown,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow down",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            if (friExpanded) {
                                Text(
                                    text = "No events on Friday, 08 Dec 2023",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.customColorsPalette.textColor,
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor
                    )
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .clickable { satExpanded = !satExpanded }
                    ) {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        text = "Saturday, 09 Dec 2023",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterVertically)
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)

                                    ) {
                                        Text(
                                            text = "0",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.customColorsPalette.white,
                                            modifier = Modifier
                                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                                .align(alignment = Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                                Spacer(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Transparent)
                                )
                                if (satExpanded) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.KeyboardArrowDown,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow down",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            if (satExpanded) {
                                Text(
                                    text = "No events on Saturday, 09 Dec 2023",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.customColorsPalette.textColor,
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor
                    )
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors= CardDefaults.cardColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .clickable { sunExpanded = !sunExpanded }
                    ) {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        text = "Sunday, 10 Dec 2023",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterVertically)
                                            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)

                                    ) {
                                        Text(
                                            text = "0",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.customColorsPalette.white,
                                            modifier = Modifier
                                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                                .align(alignment = Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                                Spacer(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Transparent)
                                )
                                if (sunExpanded) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.KeyboardArrowDown,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow down",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            if (sunExpanded) {
                                Text(
                                    text = "No events on Sunday, 10 Dec 2023",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.customColorsPalette.textColor,
                                )
                            }
                        }
                    }

                }
            }
        }
        item{
            Spacer(
                Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TimeTableScreenPreview() {
    MyTUTheme {
        TimeTableScreen(navigateBack = {})
    }
}