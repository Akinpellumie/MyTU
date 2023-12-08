package com.akinpelumi.c2068220.mytu.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akinpelumi.c2068220.mytu.base.MyTUAppState
import com.akinpelumi.c2068220.mytu.ui.views.alert.AlertScreen
import com.akinpelumi.c2068220.mytu.ui.views.attendance.AttendanceScreen
import com.akinpelumi.c2068220.mytu.ui.views.balances.BalanceScreen
import com.akinpelumi.c2068220.mytu.ui.views.calendar.CalendarScreen
import com.akinpelumi.c2068220.mytu.ui.views.home.HomeScreen
import com.akinpelumi.c2068220.mytu.ui.views.library.LibraryScreen
import com.akinpelumi.c2068220.mytu.ui.views.mail.MailScreen
import com.akinpelumi.c2068220.mytu.ui.views.modules.ModuleScreen
import com.akinpelumi.c2068220.mytu.ui.views.profile.ProfileScreen
import com.akinpelumi.c2068220.mytu.ui.views.timetable.TimeTableScreen
import com.akinpelumi.c2068220.mytu.ui.views.todo.ToDoScreen

@Composable
fun BottomNavGraph(navController: NavHostController,appState: MyTUAppState) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navigateTo={route -> appState.navigate(route) })
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
        composable(ATTENDANCE_SCREEN) { AttendanceScreen() }
        composable(TIMETABLE_SCREEN) { TimeTableScreen() }
        composable(MODULE_SCREEN) { ModuleScreen() }
        composable(LIBRARY_SCREEN) { LibraryScreen() }
        composable(BALANCE_SCREEN) { BalanceScreen() }
        composable(TODO_SCREEN) { ToDoScreen() }
    }
}