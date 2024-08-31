package com.mrgelatine.data.github_api.datasource

import com.mrgelatine.data.github_api.GitHubAPISearchController
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import com.mrgelatine.data.github_api.responses.SearchRepositoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class RepositorySearchDataSource(
    private val controller: GitHubAPISearchController
){
    fun getSearchedRepositories(context: CoroutineScope, userName: String): Deferred<List<GitHubSearchResult>?> {
        return context.async {
            controller.startRepositorySearch(userName)
        }
    }
}