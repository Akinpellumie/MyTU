package com.akinpelumi.c2068220.mytu.ui.navigations

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.akinpelumi.c2068220.mytu.ui.theme.customColorsPalette
import com.akinpelumi.c2068220.mytu.ui.views.BottomNavigation

@Composable
fun BottomBar (navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Mail,
        BottomBarScreen.Calendar,
        BottomBarScreen.Alert,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

//@Composable
//fun RowScope.AddItem(
//    screen: BottomBarScreen,
//    currentDestination: NavDestination?,
//    navController: NavHostController
//){
//    BottomNavigationItem( icon = { screen(painterResource(id = screen.icon), contentDescription = screen.title) },
//        label = { Text(text = screen.title,
//            fontSize = 9.sp) },){
//
//    }
//}
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = { Icon(painterResource(id = screen.icon), contentDescription = screen.title) },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.customColorsPalette.primaryColor,
            selectedTextColor = MaterialTheme.customColorsPalette.primaryColor,
            indicatorColor = Color.Transparent,
            unselectedIconColor = MaterialTheme.customColorsPalette.secondaryColor,
            unselectedTextColor = MaterialTheme.customColorsPalette.secondaryColor,
            ),
        alwaysShowLabel = true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}