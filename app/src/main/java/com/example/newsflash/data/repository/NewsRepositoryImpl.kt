package com.example.newsflash.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsflash.data.api.NewsApi
import com.example.newsflash.data.model.NewsResponse
import com.example.newsflash.data.model.SourceResponse
import com.example.newsflash.data.paging.NewsPaging
import com.example.newsflash.data.paging.SearchNewsPaging
import com.example.newsflash.utils.DataResult
import com.example.newsflash.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {
    override fun getNewsBySources(sources: String): Flow<PagingData<NewsResponse.Article>> = Pager (
        config = PagingConfig(
            pageSize = PAGE_SIZE,
        ),
        pagingSourceFactory = {
            NewsPaging(newsApi, sources)
        }
        ).flow

    override fun getSourcesByCategory(category: String): Flow<DataResult<SourceResponse>> = flow {
        Log.d("SelectedCategory", "Masuk fetching: $category")
        try {
            emit(DataResult.Loading())
            val result = if (category == "All") {
                newsApi.getAllSources()
            } else {
                newsApi.getSourcesByCategory(category = category)
            }
            Log.d("SelectedCategory", "Success fetching: $category")
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("SelectedCategory", "Failed to retrieve sources")
            emit(DataResult.Error(
                message = "Failed to retrieve sources"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            Log.d("SelectedCategory", "Failed to retrieve sources")
            emit(DataResult.Error(
                message = "Failed to retrieve sources"
            ))
        }
    }

    override fun searchNews(query: String): Flow<PagingData<NewsResponse.Article>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
        ),
        pagingSourceFactory = {
            SearchNewsPaging(newsApi, query)
        }
    ).flow

    override fun getNewsCategories(): List<String> = listOf(
        "Business",
        "Entertainment",
        "General",
        "Health",
        "Science",
        "Sports",
        "Technology",
        "All",
    )

}