package com.mrgelatine.data.github_api

import com.mrgelatine.data.github_api.responses.SearchRepositoryResponse
import com.mrgelatine.data.github_api.responses.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubAPI {
    @GET("/repositories")
    fun searchUsers(@Query("q") userName: String, @Query("page")currentPage: Int): Call<SearchUserResponse>

    @GET("/repositories")
    fun searchRepositories(@Query("q") repositoryName: String, @Query("page")currentPage: Int): Call<SearchRepositoryResponse>
}