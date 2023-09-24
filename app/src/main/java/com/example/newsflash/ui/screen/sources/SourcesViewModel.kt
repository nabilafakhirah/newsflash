package com.example.newsflash.ui.screen.sources

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsflash.data.repository.NewsRepository
import com.example.newsflash.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _state = mutableStateOf(SourcesState())
    val state: State<SourcesState> = _state

    fun getSourcesFromCategory(category: String) {
        val sourceResponse = repository.getSourcesByCategory(category)
        sourceResponse.onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    Log.d("SelectedCategory", "Success retrieve di ViewModel")
                    Log.d("SelectedCategory", "List size ${result.data?.sources?.size}")
                    Log.d("SelectedCategory", "Retrieve status ${result.data?.status}")
                    _state.value = SourcesState(
                        isLoading = false,
                        sourceList = result.data?.sources ?: emptyList()
                    )
                }
                is DataResult.Error -> _state.value = SourcesState(
                    isLoading = false,
                    error = result.message
                )
                is DataResult.Loading -> _state.value = SourcesState(
                    isLoading = true,
                    error = result.message
                )
            }
        }.launchIn(viewModelScope)
    }
}