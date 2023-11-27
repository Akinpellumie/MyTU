package com.akinpelumi.c2068220.mytu.ui.navigations

import androidx.compose.ui.graphics.vector.ImageVector
import com.akinpelumi.c2068220.mytu.R

sealed class BottomBarScreen(val title: String, val icon: Int, val route  : String){
    object Home : BottomBarScreen("Home", R.drawable.ic_home,"home")
    object Mail : BottomBarScreen("Mail", R.drawable.ic_mail,"mail")
    object Calendar : BottomBarScreen("Calendar", R.drawable.ic_calendar,"calendar")
    object Alert : BottomBarScreen("Alerts", R.drawable.ic_alert,"alert")
    object Profile : BottomBarScreen("Profile", R.drawable.ic_account,"profile")
}