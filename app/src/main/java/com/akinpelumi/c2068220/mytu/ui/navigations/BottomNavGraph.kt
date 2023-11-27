package com.akinpelumi.c2068220.mytu.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akinpelumi.c2068220.mytu.ui.views.AlertScreen
import com.akinpelumi.c2068220.mytu.ui.views.CalendarScreen
import com.akinpelumi.c2068220.mytu.ui.views.HomeScreen
import com.akinpelumi.c2068220.mytu.ui.views.MailScreen
import com.akinpelumi.c2068220.mytu.ui.views.ProfileScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Mail.route){
            MailScreen()
        }
        composable(route = BottomBarScreen.Calendar.route){
            CalendarScreen()
        }
        composable(route = BottomBarScreen.Alert.route){
            AlertScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}