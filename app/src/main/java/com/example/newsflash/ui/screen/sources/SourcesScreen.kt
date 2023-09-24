package com.example.newsflash.ui.screen.sources

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsflash.ui.widget.SourceItemView

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
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            sourcesList.forEach {
                SourceItemView(
                    id = it.id.orEmpty(),
                    name = it.name.orEmpty(),
                    description = it.description.orEmpty(),
                    url = it.url.orEmpty(),
                    onClickSource = {}
                )
            }
        }
    }
}