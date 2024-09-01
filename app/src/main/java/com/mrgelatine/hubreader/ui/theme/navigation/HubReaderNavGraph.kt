package com.mrgelatine.hubreader.ui.theme.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mrgelatine.search.searchscreen.SearchScreen
import com.mrgelatine.search.datascreen.RepositoryDataScreen

@Composable
fun HubReaderNavGraph(
    context: Context,
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = "search_screen"
    ){
        composable(route = "search_screen"){
            SearchScreen(
                context = context,
                nagivateToRepositoryData = {owner:String, repo:String, path:String -> navController.navigate("repository_data/$owner/$repo?path=${path}")},
            )
        }
        composable(
            route = "repository_data/{owner}/{repo}?path={path}",
            arguments = listOf(navArgument("path")
            {
                defaultValue = "jopa"
                type = NavType.StringType
            })
        ){
            backStackEntry ->
            RepositoryDataScreen(
                owner = backStackEntry.arguments?.getString("owner")!!,
                repo = backStackEntry.arguments?.getString("repo")!!,
                path = backStackEntry.arguments?.getString("path")!!,
                context = context,
                navigateToNextDirectory = {owner:String, repo:String, path:String -> navController.navigate("repository_data/$owner/$repo?path=${path}")}
            )
        }
    }
}