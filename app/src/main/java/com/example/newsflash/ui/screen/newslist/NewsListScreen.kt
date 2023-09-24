package com.example.newsflash.ui.screen.newslist

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.newsflash.data.model.NewsResponse
import com.example.newsflash.ui.navigation.ARTICLES_DETAIL_ROUTE
import com.example.newsflash.ui.widget.NewsItemView
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsListScreen(
    sourceId: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NewsListViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.getNewsList(sourceId)
        Log.d("SelectedCategory", "Source ID: $sourceId")
    }

    val state = viewModel.state
    val newsList = state.value.sourceList?.collectAsLazyPagingItems()

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
        ) {
            if (newsList != null) {
                ListContent(
                    news = newsList,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ListContent(
    news: LazyPagingItems<NewsResponse.Article>,
    navController: NavController,
) {

    val result = handlePagingResult(news = news)
    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = news,
                key = { news ->
                    news.url
                }
            ) { news ->
                news?.let {
                    NewsItemView(
                        author = it.author.orEmpty(),
                        title = it.title,
                        description = it.description,
                        publishedAt = it.publishedAt,
                        url = it.url,
                        urlToImage = it.urlToImage.orEmpty(),
                        onClickOpenNews = {
                            val encodedUrl = URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                            navController.navigate(
                                "$ARTICLES_DETAIL_ROUTE/{url}".replace(
                                    oldValue = "{url}",
                                    newValue = encodedUrl
                                )
                            )
                        }
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .height(1.dp)
                    )
                }
            }
        }
    }


}

@Composable
fun handlePagingResult(
    news: LazyPagingItems<NewsResponse.Article>,
): Boolean {
    news.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                false
            }
            error != null -> {
                false
            }
            news.itemCount < 1 -> {
                false
            }
            else -> true
        }
    }
}