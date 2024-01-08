package com.akinpelumi.c2068220.mytu.ui.views.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.common.ext.Constants
import com.akinpelumi.c2068220.mytu.ui.components.CustomAppBarPreview
import com.akinpelumi.c2068220.mytu.ui.components.CustomButton
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import com.akinpelumi.c2068220.mytu.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateTo: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
){
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
            val disName = if(!viewModel.userDisplayName.isNullOrEmpty()) viewModel.userDisplayName else "Your display name"
            // Your content
            ProfileContent(
                displayName = disName!!,
                email = viewModel.userEmail ?: "your email address",
                imageUrl = viewModel.profileImageUrl ?: Uri.EMPTY,
                onEditProfileClick = {viewModel.onEditProfileClick(navigateTo)}
            )
        }
    }
}


@Composable
fun ProfileContent(
    displayName: String,
    email: String,
    imageUrl: Uri,
    onEditProfileClick: () -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.pro_bg),
                        contentScale = ContentScale.FillBounds
                    )
                    .height(100.dp) //your height for the container
            ){
                if(imageUrl != Uri.EMPTY){
                    AsyncImage(
                        model = imageUrl,
                        placeholder = painterResource(id = R.drawable.ic_placeholder),
                        error = painterResource(id = R.drawable.ic_placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.BottomCenter)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                }
                else{
                    Image(
                        painter = painterResource(R.drawable.ic_placeholder),
                        contentDescription = "avatar",
                        contentScale = ContentScale.FillBounds,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.BottomCenter)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)   // add a border (optional)
                    )
                }
            }

        }
        item {
            Column(modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
            ){
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = displayName.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = email.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                )
            }
        }
        item{
            Spacer(modifier = Modifier.height(20.dp))
        }
        item{
            Column(modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
            ){
                //top layer tagged basic info
                Text(
                    text = "Basic Information",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor,
                        shape = RoundedCornerShape(5.dp)
                    ),
                ){
                    Column {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Full Name",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = displayName.uppercase(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        //divider line
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = MaterialTheme.customColorsPalette.borderColor,
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Email Address",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = email,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                //bottom layer tagged contact info
                Text(
                    text = "Contact Information",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColorsPalette.textColor,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.customColorsPalette.borderColor,
                        shape = RoundedCornerShape(5.dp)
                    ),
                ){
                    Column {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Phone Number",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "Add phone number",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.customColorsPalette.textColor,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }
                }

            }
        }
        item{
            CustomButton(
                title = Constants.EDIT_INFO,
                onClick = {
                    onEditProfileClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MyTUTheme {
        ProfileScreen(navigateTo = {})
    }
}