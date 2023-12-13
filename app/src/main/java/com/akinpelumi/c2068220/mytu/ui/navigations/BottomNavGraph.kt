package com.akinpelumi.c2068220.mytu.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akinpelumi.c2068220.mytu.ui.views.calendar.CalendarWebViewScreen
import com.akinpelumi.c2068220.mytu.ui.views.mail.MailWebViewScreen
import com.akinpelumi.c2068220.mytu.ui.views.alert.AlertScreen
import com.akinpelumi.c2068220.mytu.ui.views.attendance.AttendanceScreen
import com.akinpelumi.c2068220.mytu.ui.views.balances.BalanceScreen
import com.akinpelumi.c2068220.mytu.ui.views.calendar.CalendarScreen
import com.akinpelumi.c2068220.mytu.ui.views.home.HomeScreen
import com.akinpelumi.c2068220.mytu.ui.views.library.LibraryScreen
import com.akinpelumi.c2068220.mytu.ui.views.mail.MailScreen
import com.akinpelumi.c2068220.mytu.ui.views.modules.ModuleScreen
import com.akinpelumi.c2068220.mytu.ui.views.profile.EditProfileScreen
import com.akinpelumi.c2068220.mytu.ui.views.profile.ProfileScreen
import com.akinpelumi.c2068220.mytu.ui.views.timetable.TimeTableScreen
import com.akinpelumi.c2068220.mytu.ui.views.todo.ToDoScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navigateTo={route -> navController.navigate(route) })
        }
        composable(route = BottomBarScreen.Mail.route){
            MailScreen(navigateTo={route -> navController.navigate(route) })
        }
        composable(route = BottomBarScreen.Calendar.route){
            CalendarScreen(navigateTo={route -> navController.navigate(route) })
        }
        composable(route = BottomBarScreen.Alert.route){
            AlertScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(navigateTo={route -> navController.navigate(route) })
        }
        composable(
            route = AppBaseScreen.EditProfileScreen.route
        ) {
            EditProfileScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.AttendanceScreen.route
        ) {
            AttendanceScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.TimetableScreen.route
        ) {
            TimeTableScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.ToDoScreen.route
        ) {
            ToDoScreen (
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.BalanceScreen.route
        ) {
            BalanceScreen (
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.LibraryScreen.route
        ) {
            LibraryScreen (
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.ModuleScreen.route
        ) {
            ModuleScreen (
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AppBaseScreen.CalendarWebViewScreen.route
        ) {
            CalendarWebViewScreen ()
        }
        composable(
            route = AppBaseScreen.MailWebViewScreen.route
        ) {
            MailWebViewScreen ()
        }
    }
}