package com.mrgelatine.data.github_api

import com.mrgelatine.data.github_api.responses.SearchRepositoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class RepositorySearchDataSource(
    private val controller: GitHubAPISearchController
){
    fun getSearchedUsers(context: CoroutineScope, userName: String): Deferred<SearchRepositoryResponse?> {
        return context.async {
            controller.startRepositorySearch(userName)
        }
    }
}