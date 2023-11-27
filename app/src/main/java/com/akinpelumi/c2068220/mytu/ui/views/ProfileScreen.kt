package com.akinpelumi.c2068220.mytu.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.ui.components.CustomAppBarPreview
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier){
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
            ProfileContent()
        }
    }
}


@Composable
fun ProfileContent() {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth().paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.pro_bg),
                        contentScale = ContentScale.FillBounds)
                    .height(100.dp) //your height for the container
            ){
                Image(
                    painter = painterResource(R.drawable.ic_pro_dp),
                    contentDescription = "avatar",
                    contentScale = ContentScale.FillBounds,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(100.dp).align(Alignment.BottomCenter)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)   // add a border (optional)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MyTUTheme {
        ProfileScreen()
    }
}