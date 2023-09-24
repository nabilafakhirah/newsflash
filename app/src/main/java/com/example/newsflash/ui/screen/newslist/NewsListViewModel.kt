package com.example.newsflash.ui.screen.newslist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsflash.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    fun getNewsListBySource(sourceId: String) {
        val newsResponse = repository.getNewsBySources(sourceId)
        _state.value = NewsListState(
            isLoading = false,
            sourceList = newsResponse,
            error = null
        )
    }

    fun getNewsListByQuery(sourceId: String, query: String) {
        val newsResponse = repository.searchNews(query = query, sourceId = sourceId)
        _state.value = NewsListState(
            isLoading = false,
            sourceList = newsResponse,
            error = null
        )
    }
}