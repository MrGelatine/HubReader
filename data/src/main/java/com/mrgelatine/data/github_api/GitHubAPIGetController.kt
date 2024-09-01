package com.mrgelatine.data.github_api

import android.util.Log
import com.google.gson.GsonBuilder
import com.mrgelatine.data.github_api.responses.DataInfo
import com.mrgelatine.data.github_api.responses.GitHubSearchResult
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubAPIGetController(
    val state: MutableStateFlow<List<DataInfo>?>
): Callback<List<DataInfo>>{

    val BASE_URL:String = "https://api.github.com/"
    suspend fun startGetRepositoryData(user: String, repository: String, filePath: String){
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val githubAPI = retrofit.create(GitHubAPI::class.java)
        val call = githubAPI.getRepositoryData(user, repository, filePath)
        call.enqueue(this)
    }

    override fun onResponse(
        call: Call<List<DataInfo>>,
        response: Response<List<DataInfo>>
    ) {
        if(response.isSuccessful) {
            state.value = response.body()!!
        }else {
            response.errorBody()?.let { Log.e("GitHubAPIGetController", it.string()) }
        }
    }

    override fun onFailure(call: Call<List<DataInfo>>, response: Throwable) {
        response.message.let { Log.e("GitHubAPIGetController", it!!) }
    }
}