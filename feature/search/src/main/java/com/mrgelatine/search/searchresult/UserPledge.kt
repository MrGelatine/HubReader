package com.mrgelatine.search.searchresult

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPledge(
    avatarUrl: String,
    name: String,
    scores: Int,
    url: String,
    context: Context,
    redirect: (Context, String) -> Unit
){
    Card(onClick = {redirect(context, url)}) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = name)
            Text(
                text = scores.toString(),
                fontWeight = FontWeight.Bold,
                color = Color.Yellow
            )
        }
    }
}