package com.mrgelatine.search.datascreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RepositoryDataScreen(
    owner: String,
    repo: String,
    path: String,
    context: Context,
    navigateToNextDirectory: (String, String, String) -> Unit,
    vm: RepositoryDataScreenViewModel = viewModel()
){

    SideEffect {
        vm.loadReposirotyData(owner, repo, path)
    }
    val data by vm.data.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center
    ){
        Text(text = owner)
        Text(text = repo)
        Text(text = path)
        data?.forEach {data ->
            if(data.type == "dir"){
                DirectoryPledge(
                    name = data.name,
                    onClick = {navigateToNextDirectory(owner, repo, data.path)}
                )
            }else{
                FilePledge(
                    data = data,
                    onClick = {vm.onFileClick(context, data.htmlUrl)}
                )
            }
            
        }
    }
}