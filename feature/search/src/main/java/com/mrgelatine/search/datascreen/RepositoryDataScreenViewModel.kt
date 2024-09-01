package com.mrgelatine.search.datascreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrgelatine.data.github_api.GitHubAPIGetController
import com.mrgelatine.data.github_api.responses.DataInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepositoryDataScreenViewModel: ViewModel(){
    private val _data: MutableStateFlow<List<DataInfo>?> = MutableStateFlow(null)
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _error: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val data: StateFlow<List<DataInfo>?> = _data.asStateFlow()
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    val error: StateFlow<Boolean> = _error.asStateFlow()

    fun loadReposirotyData(owner:String, repo:String, path:String){
        viewModelScope.launch {
            _loading.value = true
            val controller: GitHubAPIGetController = GitHubAPIGetController(_data, _loading, _error)
            controller.startGetRepositoryData(owner, repo, if(path == "init") "" else path)

        }

    }
    fun onFileClick(context: Context, url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        ContextCompat.startActivity(context, browserIntent, null)
    }

}