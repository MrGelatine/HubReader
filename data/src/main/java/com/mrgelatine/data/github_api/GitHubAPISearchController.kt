package com.mrgelatine.data.github_api

import android.util.Log
import com.google.gson.GsonBuilder
import com.mrgelatine.data.github_api.responses.SearchRepositoryResponse
import com.mrgelatine.data.github_api.responses.SearchUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubAPISearchController(){

    val BASE_URL:String = "https://api.github.com/search"
    inner class SearchRepositoryController(): Callback<SearchRepositoryResponse>{
        suspend fun start(repository: String){
            val gson = GsonBuilder().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            val plannerokAPI = retrofit.create(GitHubAPI::class.java)
            val call = plannerokAPI.searchRepositories(repository, currentPage= 1)
            call.enqueue(this)
        }

        override fun onResponse(
            p0: Call<SearchRepositoryResponse>,
            p1: Response<SearchRepositoryResponse>
        ) {
            if(p1.isSuccessful){
                val result:SearchRepositoryResponse = p1.body()!!
            }else{
                p1.errorBody()?.let { Log.e("SearchRepositoryController", it.string()) }
            }

        }

        override fun onFailure(p0: Call<SearchRepositoryResponse>, p1: Throwable) {
            p1.message?.let { Log.e("SearchRepositoryController", it) }
        }
    }
    inner class SearchUsersController(): Callback<SearchUserResponse>{
        suspend fun start(user: String){
            val gson = GsonBuilder().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            val githubAPI = retrofit.create(GitHubAPI::class.java)
            val call = githubAPI.searchUsers(user, currentPage= 1)
            call.enqueue(this)
        }

        override fun onResponse(
            p0: Call<SearchUserResponse>,
            p1: Response<SearchUserResponse>
        ) {
            if(p1.isSuccessful){
                val result:SearchUserResponse = p1.body()!!
            }else{
                p1.errorBody()?.let { Log.e("SearchUserResponse", it.string()) }
            }

        }

        override fun onFailure(p0: Call<SearchUserResponse>, p1: Throwable) {
            p1.message?.let { Log.e("CheckAuthController", it) }
        }
    }
}