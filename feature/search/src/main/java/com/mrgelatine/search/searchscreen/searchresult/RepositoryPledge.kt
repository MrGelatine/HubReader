package com.mrgelatine.search.searchscreen.searchresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RepositoryPledge(
    owner: String,
    repositoryName: String,
    description: String?,
    forks: String,
    redirect: (String, String, String) -> Unit
){

    Card(
        onClick = {redirect(owner, repositoryName,"init")},
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = repositoryName)
                Column{
                    Text(
                        text = forks,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(text= "forks")
                }

            }
            if(description == null) {
                Text(text = "No description")
            }else{
                Text(text = description)
            }
        }
    }
}
