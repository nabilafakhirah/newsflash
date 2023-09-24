package com.example.newsflash.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsflash.ui.screen.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE
    ) {
        composable(route = HOME_SCREEN_ROUTE) {
            HomeScreen(navController)
        }
    }
}

const val HOME_SCREEN_ROUTE = "home screen"