package com.mrgelatine.search.searchresult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RepositoryPledge(
    repositoryName: String,
    description: String?,
    forks: String,
){

    Card() {
        Column(

        ){
            Row() {
                Text(text = repositoryName)
                Text(
                    text = forks,
                    fontWeight = FontWeight.Bold,
                )
            }
            if(description == null) {
                Text(text = "No description")
            }else{
                Text(text = description)
            }
        }
    }
}