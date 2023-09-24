package com.example.newsflash.ui.screen.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsflash.ui.navigation.SOURCES_SCREEN_ROUTE
import com.example.newsflash.ui.widget.CategoryItemView
import com.example.newsflash.ui.widget.SearchBarView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.state
    
    Scaffold(
        topBar = {
            Row {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigateUp()
                        }
                )
            }
        }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(it),
        ) {
            items(state.value.categoryList.size) { index ->
                val categoryName = state.value.categoryList[index]
                CategoryItemView(
                    key = categoryName,
                    onClickCategory = {
                        navController.navigate(
                            "${SOURCES_SCREEN_ROUTE}/{category}".replace(
                                oldValue = "{category}",
                                newValue = categoryName
                            )
                        )
                    }
                )
            }
        }
    }
}