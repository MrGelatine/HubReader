package com.mrgelatine.search.datascreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrgelatine.data.github_api.responses.DataInfo

@Composable
fun FilePledge(
    data: DataInfo,
    onClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth().height(100.dp).clickable {
        onClick()
    }) {
        Icon(Icons.Sharp.Info, contentDescription = "Data")
        Text(text = data.name)
    }
}
