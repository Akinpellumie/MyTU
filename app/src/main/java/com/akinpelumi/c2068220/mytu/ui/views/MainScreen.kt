package com.akinpelumi.c2068220.mytu.ui.views

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akinpelumi.c2068220.mytu.ui.navigations.BottomBar
import com.akinpelumi.c2068220.mytu.ui.navigations.BottomBarScreen
import com.akinpelumi.c2068220.mytu.ui.navigations.BottomNavGraph
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}
@Composable
fun BottomNavigation(navController: () -> Unit) {
    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Mail,
        BottomBarScreen.Calendar,
        BottomBarScreen.Alerts,
        BottomBarScreen.Profile
    )
    BottomNavigation(
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomBarScreen.Home.screen_route) {
        composable(BottomBarScreen.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomBarScreen.MyNetwork.screen_route) {
            NetworkScreen()
        }
        composable(BottomBarScreen.AddPost.screen_route) {
            AddPostScreen()
        }
        composable(BottomBarScreen.Notification.screen_route) {
            NotificationScreen()
        }
        composable(BottomBarScreen.Jobs.screen_route) {
            JobScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyTUTheme {
        MainScreen()
    }
}