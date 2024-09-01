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
    private val _request = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    private val _sendButtonState = MutableStateFlow(false)
    val uiState: StateFlow<PagingData<GitHubSearchResult>> = _uiState.asStateFlow()
    val request: StateFlow<String> = _request.asStateFlow()
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    val sendButtonState: StateFlow<Boolean> = _sendButtonState.asStateFlow()

    val repository = GitHubSearchRepository(viewModelScope)


    fun onRequestChange(r: String){
        _request.value = r
        if(r.length < 3){
            _sendButtonState.value = false
        }else{
            _sendButtonState.value = true
        }
    }

    fun onSearchClicked() {
        viewModelScope.launch {
            try {
                _loading.value = true
                repository.getSearchResult(request.value)
                    .debounce(2000)
                    .distinctUntilChanged()
                    .collect { pagingData ->
                        _uiState.value = pagingData
                        _loading.value = false
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