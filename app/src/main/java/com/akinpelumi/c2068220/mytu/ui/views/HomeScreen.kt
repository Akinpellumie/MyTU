package com.akinpelumi.c2068220.mytu.ui.views

import android.graphics.fonts.FontFamily
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.ui.components.CustomAppBarPreview
import com.akinpelumi.c2068220.mytu.ui.components.CustomMenuCard
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette


//@Composable
//fun HomeScreen() {
//    // 1
//    Column(modifier = Modifier.fillMaxSize()) {
//        // 2
//        TopAppBar(title = {
////            Text(
////                text = "Hello ",
////                modifier = modifier
////            )
//        })
//        // 3
//        //RecipeList(defaultRecipes)
//    }
//
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(modifier: Modifier = Modifier) {
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
            ScrollContent()
        }
    }
}

@Composable
fun ScrollContent() {
    val range = 1..100

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)){
        item {
            Column {
                Text(text = "Welcome Back,",
                    style = typography.bodyMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    //modifier = Modifier.padding(vertical = 5.dp)
                )
                Text(text = "Akinlade, Akinpelumi",
                    style = typography.titleMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        item {
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
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                Row {
                                    Text(
                                        text = "Today’s Timetable",
                                        style = typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Card(
                                        colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.iconBgColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)

                                    ){
                                        Text(
                                            text = "2",
                                            style = typography.titleMedium,
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
                                        .background(Color.Transparent))
                                if (topExpanded){
                                    Icon(Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                        )
                                }
                                else{
                                    Icon(Icons.Filled.KeyboardArrowDown,
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
                                        Row {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_outline_calendar),
                                                tint = MaterialTheme.customColorsPalette.textColor,
                                                contentDescription = "calender",
                                                modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                            )
                                            Text(
                                                text = "10:00 - 12:00",
                                                style = typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                            )
                                        }
                                        Text(
                                            text = "CIS4008-N IT Lab(OL9)",
                                            style = typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Text(
                                            text = "Big Data and Business Intelligence - IT Lab. This event takes place in OL9, on the 1st Floor of the Europa Building.",
                                            style = MaterialTheme.typography.bodyMedium,
                                        )
                                        Row {
                                            Text(
                                                text = "Campus Map",
                                                style = typography.titleMedium,
                                                color = MaterialTheme.customColorsPalette.primaryColor,
                                                fontWeight = FontWeight.Bold,
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_map_pin),
                                                contentDescription = "calender",
                                                tint = MaterialTheme.customColorsPalette.altIconColor,
                                                modifier = Modifier
                                                    .align(
                                                        alignment = Alignment.CenterVertically
                                                    )
                                                    .height(20.dp)
                                                    .width(20.dp)
                                                    .padding(start = 5.dp)
                                            )
                                        }
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
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                Row {
                                    Text(
                                        text = "To Do (Upcoming/Overdue)",
                                        style = typography.titleMedium,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Card(
                                        colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.iconBgColor),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)

                                    ){
                                        Text(
                                            text = "0",
                                            style = typography.titleMedium,
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
                                        .background(Color.Transparent))
                                if (bottomExpanded){
                                    Icon(Icons.Filled.KeyboardArrowUp,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow up",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                    )
                                }
                                else{
                                    Icon(Icons.Filled.KeyboardArrowDown,
                                        tint = MaterialTheme.customColorsPalette.textColor,
                                        contentDescription = "arrow down",
                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
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
                                    Column(verticalArrangement = Arrangement.Center) {
                                        Text(
                                            text = "You have no tasks that are overdue or due within the next 7 days",
                                            style = MaterialTheme.typography.bodyMedium,
                                            //modifier = Modifier.align()
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = 5.dp)) }
        /*my attendance section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "My Attendance",
                description = "Record your class attendance",
                iconName = R.drawable.ic_calendar_check
            )
        }
        /*timetable section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "Timetable",
                description = "View your class timetable",
                iconName = R.drawable.ic_calendar_month
            )
        }
        /*To Do  section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "To Do",
                description = "View outstanding tasks and reminders",
                iconName = R.drawable.ic_list_task
            )
        }
        /*Fee/Balance  section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "Balances",
                description = "View your University balances",
                iconName = R.drawable.ic_cash
            )
        }
        /*Library  section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "Library",
                description = "View your library loans and reservations",
                iconName = R.drawable.ic_library
            )
        }
        /*Modules  section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "Modules",
                description = "View your modules",
                iconName = R.drawable.ic_modules
            )
        }
        /*Additional Info  section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "Additional Information",
                description = "Induction, My Digital Life, and more",
                iconName = R.drawable.ic_info
            )
        }
        /*Universe  section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "UNIverse",
                description = "View our  FAQs/raise and enquiry",
                iconName = R.drawable.ic_universe
            )
        }
        /*stream  section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "StREAM",
                description = "Your personalised engagement dashboard",
                iconName = R.drawable.ic_stream
            )
        }
        /*module evaluation  section*/
        item {
            CustomMenuCard(
                onClick = {  },
                title = "Module Evaluation",
                description = "Tell us what you think of your modules",
                iconName = R.drawable.ic_stream
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MyTUTheme {
        Home()
    }
}