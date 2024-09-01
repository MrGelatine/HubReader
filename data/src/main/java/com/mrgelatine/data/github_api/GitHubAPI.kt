package com.mrgelatine.data.github_api

import com.mrgelatine.data.github_api.responses.DataInfo
import com.mrgelatine.data.github_api.responses.SearchRepositoryResponse
import com.mrgelatine.data.github_api.responses.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {
    @GET("users")
    fun searchUsers(@Query("q") userName: String, @Query("per_page")perPage: Int = 5, @Query("page")currentPage: Int): Call<SearchUserResponse>

    @GET("repositories")
    fun searchRepositories(@Query("q") repositoryName: String, @Query("per_page")perPage: Int = 10, @Query("page")currentPage: Int): Call<SearchRepositoryResponse>

    @GET("/repos/{owner}/{repo}/contents/{path}")
    fun getRepositoryData(@Path("owner") user:String, @Path("repo") repository: String, @Path("path") filePath: String): Call<List<DataInfo>>
}