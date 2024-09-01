package com.mrgelatine.search.datascreen.dataresult

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DirectoryPledge(
    name: String,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .wrapContentSize()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }){
            Icon(Icons.Sharp.Info, contentDescription = "Directory")
            Text(text = name)
        }
    }

}