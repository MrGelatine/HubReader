package com.mrgelatine.search.searchscreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mrgelatine.data.github_api.pager.GitHubSearchRepository
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class SearchScreenViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<PagingData<GitHubSearchResult>>(PagingData.empty())
    private val _request = MutableStateFlow<String>("")
    val uiState: StateFlow<PagingData<GitHubSearchResult>> = _uiState.asStateFlow()
    val request: StateFlow<String> = _request.asStateFlow()

    val repository = GitHubSearchRepository(viewModelScope)

    fun onRequestChange(r: String){
        _request.value = r
    }

    fun onSearchClicked() {
        viewModelScope.launch {
            try {
                repository.getSearchResult(request.value)
                    .debounce(2000)
                    .distinctUntilChanged()
                    .cachedIn(viewModelScope)
                    .collect { pagingData ->
                        _uiState.value = pagingData
                }
            } catch (e: Exception) {
                Log.d("XXX:", e.toString())
            }
        }
    }

    fun redirectToUserProgile(context: Context, url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        startActivity(context, browserIntent, null)
    }

}