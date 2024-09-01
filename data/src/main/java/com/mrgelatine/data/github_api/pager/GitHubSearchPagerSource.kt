package com.mrgelatine.data.github_api.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mrgelatine.data.github_api.datasource.RepositorySearchDataSource
import com.mrgelatine.data.github_api.datasource.UserSearchDataSource
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException
import java.io.IOException


class GitHubSearchPagerSource(
    private val searchUserDataSource: UserSearchDataSource,
    private val searchRepositoryDataSource: RepositorySearchDataSource,
    private val coroutineScope: CoroutineScope,
    private val request: String
) : PagingSource<Int, GitHubSearchResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubSearchResult> {
        return try {
            val currentPage = params.key ?: 1
            val usersRequest = searchUserDataSource.getSearchedUsers(coroutineScope, request, currentPage)
            val repositoriesRequest = searchRepositoryDataSource.getSearchedRepositories(coroutineScope, request, currentPage)
            val users = usersRequest.await()
            val repositories = repositoriesRequest.await()
            val collectedResults:MutableList<GitHubSearchResult> = mutableListOf()
            repositories?.let{ collectedResults.addAll(it)}
            users?.let{ collectedResults.addAll(it)}
            val searchResults = collectedResults.sortedBy { it.name }
            LoadResult.Page(
                data = searchResults,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (searchResults.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitHubSearchResult>): Int? {
        return state.anchorPosition
    }

}