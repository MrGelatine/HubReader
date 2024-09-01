package com.mrgelatine.search.datascreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mrgelatine.search.datascreen.dataresult.DirectoryPledge
import com.mrgelatine.search.datascreen.dataresult.FilePledge

@Composable
fun RepositoryDataScreen(
    owner: String,
    repo: String,
    path: String,
    context: Context,
    navigateToNextDirectory: (String, String, String) -> Unit,
    vm: RepositoryDataScreenViewModel = viewModel()
){
    LaunchedEffect(key1 = path){
        vm.loadReposirotyData(owner, repo, path)
    }
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()
    val data by vm.data.collectAsState()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ){
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
        }else{
            if(error){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(text = "Load error")
                        Button(onClick ={ vm.loadReposirotyData(owner, repo, path) }) {
                            Text(text = "Refresh")
                        }
                    }

                }
            }else{
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

    }
}