package com.mrgelatine.hubreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.mrgelatine.hubreader.ui.theme.HubReaderTheme
import com.mrgelatine.hubreader.ui.theme.navigation.HubReaderNavGraph
import com.mrgelatine.search.searchscreen.SearchScreen

class SearchScreenViewModel: ViewModel(){


}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HubReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    HubReaderNavGraph(context = this, navController = navController)
                }
            }
        }
    }
}