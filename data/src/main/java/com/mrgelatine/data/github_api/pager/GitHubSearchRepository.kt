package com.mrgelatine.data.github_api.pager

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrgelatine.data.github_api.GitHubAPISearchController
import com.mrgelatine.data.github_api.datasource.RepositorySearchDataSource
import com.mrgelatine.data.github_api.datasource.UserSearchDataSource
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GitHubSearchRepository constructor(
    private val coroutineScope: CoroutineScope,
    private val searchUserDataSource: UserSearchDataSource = UserSearchDataSource(
        GitHubAPISearchController()
    ),
    private val searchRepositoryDataSource: RepositorySearchDataSource = RepositorySearchDataSource(
        GitHubAPISearchController()
    )
    ) {
    suspend fun getSearchResult(request: String): Flow<PagingData<GitHubSearchResult>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 5),
            pagingSourceFactory = {
                GitHubSearchPagerSource(
                    searchUserDataSource,
                    searchRepositoryDataSource,
                    coroutineScope,
                    request
                )
            }
        ).flow
    }
}