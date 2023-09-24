package com.example.newsflash.data.api

import com.example.newsflash.BuildConfig
import com.example.newsflash.data.model.NewsResponse
import com.example.newsflash.data.model.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("everything")
    suspend fun searchNews(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse

    @GET("everything")
    suspend fun getNewsBySources(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse

    @GET("top-headlines/sources")
    suspend fun getSourcesByCategory(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("category") category: String,
    ): SourceResponse

    @GET("top-headlines/sources")
    suspend fun getAllSources(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): SourceResponse
}