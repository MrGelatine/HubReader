package com.mrgelatine.search.searchscreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import com.mrgelatine.data.github_api.responses.SearchRepositoryResponse
import com.mrgelatine.data.github_api.responses.SearchUserResponse
import com.mrgelatine.search.searchscreen.searchresult.RepositoryPledge
import com.mrgelatine.search.searchscreen.searchresult.UserPledge
import com.valentinilk.shimmer.shimmer


@Composable
fun SearchScreen(
    context: Context,
    nagivateToRepositoryData: (String, String, String) -> Unit,
    vm: SearchScreenViewModel = viewModel(),

){
    val searchPagingItems: LazyPagingItems<GitHubSearchResult> = vm.uiState.collectAsLazyPagingItems()
    val loading: Boolean by vm.loading.collectAsState()
    val request: String by vm.request.collectAsState()
    val sendButtonState: Boolean by vm.sendButtonState.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ){
            OutlinedTextField(
                value = request,
                onValueChange = vm::onRequestChange,
                enabled = !loading,
                modifier = Modifier.padding(end = 5.dp)
            )
            Button(
                onClick = vm::onSearchClicked,
                enabled = sendButtonState && !loading,
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Icon(Icons.Sharp.Search, contentDescription = "Search")
            }
        }

        if(loading){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                CircularProgressIndicator(
                    modifier = Modifier.width(100.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

        }
        else{
            when(searchPagingItems.loadState.refresh){
                is LoadState.NotLoading ->{
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    )
                    {
                        items(searchPagingItems.itemCount) { index ->
                            val searchResult = searchPagingItems[index]
                            if (searchResult != null) {
                                when (searchResult) {
                                    is SearchRepositoryResponse.GitHubSearchRepository -> RepositoryPledge(
                                        owner = searchResult.owner.login,
                                        repositoryName = searchResult.name,
                                        description = searchResult.description,
                                        forks = searchResult.forksCount,
                                        redirect = nagivateToRepositoryData

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
                is LoadState.Error -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(text = "Load error")
                            Button(onClick = vm::onSearchClicked) {
                                Text(text = "Refresh")
                            }
                        }

                    }
                }
                is LoadState.Loading ->{
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier.width(100.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                }
            }
        }
    }
}