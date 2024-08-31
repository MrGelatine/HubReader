package com.mrgelatine.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mrgelatine.data.github_api.pager.GitHubSearchRepository
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<PagingData<GitHubSearchResult>>(PagingData.empty())
    val uiState: StateFlow<PagingData<GitHubSearchResult>> = _uiState.asStateFlow()
    val request: String = ""
    val repository = GitHubSearchRepository(viewModelScope)

    fun onSearchClicked() {
        viewModelScope.launch {
            try {
                val movieDetails = repository.getSearchResult("Tommy")
            } catch (e: Exception) {
                Log.d("XXX:", e.toString())
            }
        }
    }

}