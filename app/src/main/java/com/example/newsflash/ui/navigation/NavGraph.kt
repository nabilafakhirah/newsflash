package com.example.newsflash.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsflash.ui.screen.home.HomeScreen
import com.example.newsflash.ui.screen.newslist.NewsListScreen
import com.example.newsflash.ui.screen.sources.SourcesScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE
    ) {
        composable(HOME_SCREEN_ROUTE) {
            HomeScreen(navController)
        }
        composable("${SOURCES_SCREEN_ROUTE}/{category}") {
            val category = it.arguments?.getString("category") ?: "All"
            SourcesScreen(
                navController = navController,
                category = category
            )
        }
        composable("${ARTICLES_SCREEN_ROUTE}/{sourceId}") {
            val sourceId = it.arguments?.getString("sourceId") ?: ""
            NewsListScreen(
                sourceId = sourceId,
                navController = navController
            )
        }
    }
}

const val HOME_SCREEN_ROUTE = "home screen"
const val SOURCES_SCREEN_ROUTE = "sources screen route"
const val ARTICLES_SCREEN_ROUTE = "articles screen route"
const val ARTICLES_DETAIL_ROUTE = "articles detail route"