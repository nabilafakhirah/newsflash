package com.example.newsflash.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsflash.ui.screen.home.HomeScreen
import com.example.newsflash.ui.screen.newslist.NewsListScreen
import com.example.newsflash.ui.screen.sources.SourcesScreen
import com.example.newsflash.ui.screen.webview.NewsWebView

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
        composable("${ARTICLES_DETAIL_ROUTE}/{url}") {
            val url = it.arguments?.getString("url") ?: ""
            NewsWebView(
                url = url,
                navController = navController
            )
        }
    }
}

const val HOME_SCREEN_ROUTE = "home_screen"
const val SOURCES_SCREEN_ROUTE = "sources_screen_route"
const val ARTICLES_SCREEN_ROUTE = "articles_screen_route"
const val ARTICLES_DETAIL_ROUTE = "articles_detail_route"