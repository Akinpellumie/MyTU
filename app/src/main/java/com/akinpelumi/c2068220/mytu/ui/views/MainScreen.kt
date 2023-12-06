package com.akinpelumi.c2068220.mytu.ui.views

import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.akinpelumi.c2068220.mytu.base.rememberAppState
import com.akinpelumi.c2068220.mytu.ui.navigations.BottomBar
import com.akinpelumi.c2068220.mytu.ui.navigations.BottomNavGraph
import com.akinpelumi.c2068220.mytu.ui.theme.MyTUTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = { BottomBar(navController = navController) }
//    ) {it
//        BottomNavGraph(navController = navController)
//    }
    MainScreenContent()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(){
    //val navController = rememberNavController()
    val appState = rememberAppState()
    Scaffold(
        bottomBar = { BottomBar(navController = appState.navController) }
    ) {it
        BottomNavGraph(navController = appState.navController, appState= appState)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyTUTheme {
        MainScreen()
    }
}