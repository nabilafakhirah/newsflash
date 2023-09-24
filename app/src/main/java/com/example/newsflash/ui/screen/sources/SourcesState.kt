package com.example.newsflash.ui.screen.sources

import com.example.newsflash.data.model.SourceResponse

data class SourcesState(
    var isLoading: Boolean = false,
    val sourceList: List<SourceResponse.Source> = emptyList(),
    val error: String? = ""
)