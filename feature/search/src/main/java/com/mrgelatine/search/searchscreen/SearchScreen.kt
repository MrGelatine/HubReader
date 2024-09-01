package com.mrgelatine.search.searchscreen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import com.mrgelatine.data.github_api.responses.SearchRepositoryResponse
import com.mrgelatine.data.github_api.responses.SearchUserResponse
import com.mrgelatine.search.searchresult.RepositoryPledge
import com.mrgelatine.search.searchresult.UserPledge

@Composable
fun SearchScreen(
    vm: SearchScreenViewModel = viewModel(),
    context: Context
){
    val searchPagingItems: LazyPagingItems<GitHubSearchResult> = vm.uiState.collectAsLazyPagingItems()
    val request: String by vm.request.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically){
            OutlinedTextField(value = request, onValueChange = vm::onRequestChange)
            Button(
                onClick = vm::onSearchClicked,
                enabled = true
            ) {
                Icon(Icons.Sharp.Search, contentDescription = "Search")
            }
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            items(searchPagingItems.itemCount) { index ->
                val searchResult = searchPagingItems[index]
                if (searchResult != null) {
                    when(searchResult){
                        is SearchRepositoryResponse.GitHubSearchRepository -> RepositoryPledge(
                            repositoryName = searchResult.name,
                            description = searchResult.description,
                            forks = searchResult.forksCount

                        )
                        is SearchUserResponse.GitHubSearchUser -> UserPledge(
                            avatarUrl = searchResult.avatarUrl,
                            name = searchResult.name,
                            scores = searchResult.score,
                            url = searchResult.htmlUrl,
                            context = context,
                            redirect = vm::redirectToUserProgile
                        )

                    }
                }
            }
        }
    }

}