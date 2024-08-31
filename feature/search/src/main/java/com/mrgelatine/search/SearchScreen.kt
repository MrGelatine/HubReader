package com.mrgelatine.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.mrgelatine.data.github_api.responses.GitHubSearchResult

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = viewModel()
){
    val searchPagingItems: LazyPagingItems<GitHubSearchResult> = viewModel.uiState.collectAsLazyPagingItems()
    Button(onClick = viewModel::onSearchClicked){}
    LazyColumn {
        items(searchPagingItems.itemCount) { index ->
            val searchResult = searchPagingItems[index]
            if (searchResult != null) {
                Text(text = searchResult.name)
            }
        }
    }
}