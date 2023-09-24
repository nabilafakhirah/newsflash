package com.example.newsflash.data.repository

import androidx.paging.PagingData
import com.example.newsflash.data.model.NewsResponse
import com.example.newsflash.data.model.SourceResponse
import com.example.newsflash.utils.DataResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsBySources(sources: String): Flow<PagingData<NewsResponse.Article>>

    fun getSourcesByCategory(category: String): Flow<DataResult<SourceResponse>>

    fun searchNews(query : String, sourceId: String) : Flow<PagingData<NewsResponse.Article>>

    fun getNewsCategories() : List<String>
}