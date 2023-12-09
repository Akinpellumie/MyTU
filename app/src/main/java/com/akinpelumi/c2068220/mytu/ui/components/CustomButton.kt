package com.akinpelumi.c2068220.mytu.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette

@Composable
fun CustomButton(title: String, onClick: () -> Unit,modifier: Modifier = Modifier,) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.customColorsPalette.primaryColor),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
        onClick = {
            onClick()
        }) {
        Text(
            text = title,
            color = MaterialTheme.customColorsPalette.white,
            fontWeight = FontWeight.Bold,
        )
    }
}