package com.mrgelatine.data.github_api

import android.util.Log
import com.google.gson.GsonBuilder
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import com.mrgelatine.data.github_api.responses.SearchRepositoryResponse
import com.mrgelatine.data.github_api.responses.SearchUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class GitHubAPISearchController(){

    val BASE_URL:String = "https://api.github.com/search"

    suspend fun startRepositorySearch(repository: String):List<GitHubSearchResult>? {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val plannerokAPI = retrofit.create(GitHubAPI::class.java)
        val call = plannerokAPI.searchRepositories(repository, currentPage = 1)
        val response = call.execute()
        if(response.isSuccessful) {

            return response.body()!!.items
        }else {
            response.errorBody()?.let { Log.e("SearchRepositoryController", it.string()) }
            return null
        }

    }
    suspend fun startUserSearch(user: String): List<GitHubSearchResult>?{
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val githubAPI = retrofit.create(GitHubAPI::class.java)
        val call = githubAPI.searchUsers(user, currentPage= 1)
        val response = call.execute()
        if(response.isSuccessful) {
            return response.body()!!.items
        }else {
            response.errorBody()?.let { Log.e("SearchUsersController", it.string()) }
            return null
        }
    }
}