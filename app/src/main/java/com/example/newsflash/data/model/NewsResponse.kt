package com.example.newsflash.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class NewsResponse(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
) {
    @Parcelize
    data class Article(
        @Json(name = "author")
        val author: String?,
        @Json(name = "content")
        val content: String?,
        @Json(name = "description")
        val description: String,
        @Json(name = "publishedAt")
        val publishedAt: String,
        @Json(name = "source")
        val source: SourceResponse?,
        @Json(name = "title")
        val title: String,
        @Json(name = "url")
        val url: String,
        @Json(name = "urlToImage")
        val urlToImage: String?
    ) : Parcelable
}
