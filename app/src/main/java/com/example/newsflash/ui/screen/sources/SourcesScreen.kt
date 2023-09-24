package com.example.newsflash.ui.screen.sources

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsflash.ui.navigation.ARTICLES_SCREEN_ROUTE
import com.example.newsflash.ui.widget.EmptyView
import com.example.newsflash.ui.widget.LoadingView
import com.example.newsflash.ui.widget.SearchBarView
import com.example.newsflash.ui.widget.SourceItemView
import com.example.newsflash.ui.widget.TopBarView

@Composable
fun SourcesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    category: String,
    viewModel: SourcesViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getSourcesFromCategory(category)
        Log.d("SelectedCategory", "NewsScreen: $category")
    }

    val state = viewModel.state
    val sourcesList = state.value.sourceList

    Scaffold(
        topBar = {
            TopBarView(onClickBack = { navController.navigateUp() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            SearchBarView(
                onSearch = {
                    viewModel.filterSources(it)
                }
            )
            if (state.value.isLoading) {
                LoadingView()
            }
            else {
                if (sourcesList.isEmpty()) {
                    EmptyView()
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                    ) {
                        sourcesList.forEach {
                            val sourceId = it.id.orEmpty()
                            SourceItemView(
                                id = sourceId,
                                name = it.name.orEmpty(),
                                description = it.description.orEmpty(),
                                url = it.url.orEmpty(),
                                onClickSource = {
                                    navController.navigate(
                                        "$ARTICLES_SCREEN_ROUTE/{sourceId}".replace(
                                            oldValue = "{sourceId}",
                                            newValue = sourceId
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}