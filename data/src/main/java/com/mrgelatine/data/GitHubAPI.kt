package com.mrgelatine.data

interface GitHubAPI {
    @GET
    fun searchUsers()

    @GET
    fun searchRepositories
}