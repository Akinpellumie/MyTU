package com.akinpelumi.c2068220.mytu.ui.views.balances

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Balances",
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
        BalanceScreenContent()
        }
    }
}


@Composable
fun BalanceScreenContent() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Column(modifier = Modifier.padding(bottom = 15.dp)) {
            Text(text = "Balances",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.customColorsPalette.textColor,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Shown below are your University balances.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.customColorsPalette.textColor,
                )
            }
        }
        item{
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                //myPrint
                Card(
                    colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.itemBgColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    modifier = Modifier
                        .border(
                            color = MaterialTheme.customColorsPalette.eventBorderColor,
                            width = 1.dp, shape = RectangleShape
                        )
                        .padding(10.dp)

                ){
                    Column(modifier = Modifier.padding(10.dp)){
                        Row{
                            Text(text = "My Print Credits",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(
                                Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color.Transparent))
                            Text(text = "£0.34",
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )
                        Text(text = "You are registered",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.customColorsPalette.descColor,
                        )
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )
                        OutlinedButton(
                            onClick = { },
                            border = BorderStroke(1.dp, MaterialTheme.customColorsPalette.linkTextColor),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.customColorsPalette.linkTextColor)
                        ){
                            Text(text = "Top up your myPrint Credit",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.customColorsPalette.linkTextColor
                            )
                        }
                    }
                }

                //spacer
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent)
                )

                //cashless vending
                Card(
                    colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.itemBgColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    modifier = Modifier
                        .border(
                            color = MaterialTheme.customColorsPalette.eventBorderColor,
                            width = 1.dp, shape = RectangleShape
                        )
                        .padding(10.dp)

                ){
                    Column(modifier = Modifier.padding(10.dp)){
                        Row{
                            Text(text = "Cashless Vending\nCredits",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(
                                Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color.Transparent))
                            Text(text = "£0.00",
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )
                        Text(text = "You are registered",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.customColorsPalette.descColor,
                        )
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )
                        OutlinedButton(
                            onClick = { },
                            border = BorderStroke(1.dp, MaterialTheme.customColorsPalette.linkTextColor),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.customColorsPalette.linkTextColor)
                        ){
                            Text(text = "Top up your Cashless Credit",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.customColorsPalette.linkTextColor
                            )
                        }
                    }
                }

                //spacer
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent)
                )

                //library charges
                Card(
                    colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.itemBgColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    modifier = Modifier
                        .border(
                            color = MaterialTheme.customColorsPalette.eventBorderColor,
                            width = 1.dp, shape = RectangleShape
                        )
                        .padding(10.dp)

                ){
                    Column(modifier = Modifier.padding(10.dp)){
                        Row{
                            Text(text = "Library Charges",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(
                                Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color.Transparent))
                            Text(text = "£0.00",
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )
                        Text(text = "You are registered",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.customColorsPalette.descColor,
                        )
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )

                    }
                }

                //spacer
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent)
                )

                //TUSC card
                Card(
                    colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.itemBgColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    modifier = Modifier
                        .border(
                            color = MaterialTheme.customColorsPalette.eventBorderColor,
                            width = 1.dp, shape = RectangleShape
                        )
                        .padding(10.dp)

                ){
                    Column(modifier = Modifier.padding(10.dp)){
                        Text(text = "Need a Replacement TUSC",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.customColorsPalette.textColor,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )
                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                            border = BorderStroke(1.dp, MaterialTheme.customColorsPalette.linkTextColor),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.customColorsPalette.linkTextColor)
                        ){
                            Icon(
                                imageVector = Icons.Outlined.ShoppingCart,
                                contentDescription = "cart",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                            Text(text = "Order a replacement card",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.customColorsPalette.linkTextColor
                            )
                        }
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )
                        Text(text = "Cancel Your TUSC",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.customColorsPalette.textColor,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(
                            Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )
                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                            border = BorderStroke(1.dp, MaterialTheme.customColorsPalette.redVariantColor),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.customColorsPalette.redVariantColor)
                        ){
                            Icon(
                                imageVector = Icons.Outlined.Warning,
                                contentDescription = "err",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                            Text(text = "Cancel your TUSC",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.customColorsPalette.redVariantColor
                            )
                        }
                    }
                }
            }
        }
        item{
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BalanceScreenPreview() {
    MyTUTheme {
        BalanceScreen()
    }
}