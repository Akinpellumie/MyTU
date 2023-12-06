package com.akinpelumi.c2068220.mytu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette

@Composable
fun CustomMenuCard(
    onClick: () -> Unit,
    title: String,
    description: String,
    iconName: Int
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .fillMaxWidth()
            .height(70.dp).clickable { onClick() },
        colors= CardDefaults.cardColors(containerColor = MaterialTheme.customColorsPalette.itemBgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))

    ) {
        Row{
            Box(modifier = Modifier
                .height(70.dp)
                .width(75.dp)
                .background(
                    color = MaterialTheme.customColorsPalette.iconBgColor,
                    shape = RectangleShape
                )) {
                Icon(
                    painter = painterResource(id = iconName), // Replace with your logout icon resource
                    contentDescription = "icon name",
                    tint = MaterialTheme.customColorsPalette.white,
                    modifier = Modifier.align(alignment = Alignment.Center).height(100.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                Text(text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColorsPalette.textColor,)
                Text(text = description, style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.customColorsPalette.descColor,)
            }
        }
    }
}

