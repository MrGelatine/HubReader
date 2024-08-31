package com.mrgelatine.data.github_api

import com.mrgelatine.data.github_api.responses.SearchUserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow

class UserSearchDataSource(
    private val controller: GitHubAPISearchController
){
    fun getSearchedUsers(context: CoroutineScope, userName: String): Deferred<SearchUserResponse?>{
        return context.async {
            controller.startUserSearch(userName)
        }
    }
}