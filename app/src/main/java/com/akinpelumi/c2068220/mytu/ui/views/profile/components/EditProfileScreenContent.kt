package com.akinpelumi.c2068220.mytu.ui.views.profile.components

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.akinpelumi.c2068220.mytu.R
import com.akinpelumi.c2068220.mytu.common.composables.BasicField
import com.akinpelumi.c2068220.mytu.common.composables.BasicTextButton
import com.akinpelumi.c2068220.mytu.common.ext.Constants
import com.akinpelumi.c2068220.mytu.common.ext.Constants.NAME_LABEL
import com.akinpelumi.c2068220.mytu.common.ext.Utils.Companion.showToastMessage
import com.akinpelumi.c2068220.mytu.common.ext.fieldModifier
import com.akinpelumi.c2068220.mytu.common.ext.textButton
import com.akinpelumi.c2068220.mytu.ui.components.CustomButton
import com.akinpelumi.c2068220.mytu.ui.views.profile.ProfileUiState

@Composable
fun EditProfileScreenContent(
    padding: PaddingValues,
    onDisplayNameChange: (String) -> Unit,
    onDisplayImageChange: (Uri) -> Unit,
    uiState: ProfileUiState,
    updateProfileInfo: () -> Unit,
    ) {
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
                onDisplayImageChange(it)
            }
        }
    )
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            showToastMessage(context, "Permission Granted")
        } else {
            showToastMessage(context, "Permission Denied")
        }
    }
    LazyColumn(
        contentPadding = PaddingValues(vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Column (
                modifier = Modifier.padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ){
                if(imageUri != null){
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .clickable {
                                val permissionCheckResult =
                                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                    galleryLauncher.launch("image/*")
                                } else {
                                    // Request a permission
                                    permissionLauncher.launch(Manifest.permission.CAMERA)
                                }
                            }
                            .border(2.dp, Color.White, CircleShape)   // add a border (optional)
                    )
                }
                else{
                    Image(
                        painter = painterResource(R.drawable.ic_placeholder),
                        contentDescription = "avatar",
                        contentScale = ContentScale.FillBounds,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .clickable {
                                val permissionCheckResult =
                                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                    galleryLauncher.launch("image/*")
                                } else {
                                    // Request a permission
                                    permissionLauncher.launch(Manifest.permission.CAMERA)
                                }
                            }
                            .border(2.dp, Color.White, CircleShape)   // add a border (optional)
                    )
                }

                BasicTextButton(
                    Constants.TAKE_PHOTO,
                    Modifier.textButton()) {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        galleryLauncher.launch("image/*")
                    } else {
                        // Request a permission
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
            } }

        item {
            BasicField(
                NAME_LABEL,
                uiState.fullName,
                onDisplayNameChange,
                Modifier.fieldModifier()
            )

        }

        item {
            CustomButton(
            title = Constants.SAVE,
            onClick = {
                updateProfileInfo()
                if(!uiState.isSuccess && !uiState.inProgress){
                    showToastMessage(context, uiState.error)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) }
    }
}
