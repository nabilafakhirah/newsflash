package com.example.newsflash.ui.screen.newslist

import androidx.paging.PagingData
import com.example.newsflash.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow

data class NewsListState(
    var isLoading: Boolean = true,
    val sourceList: Flow<PagingData<NewsResponse.Article>>? = null,
    val error: String? = ""
)