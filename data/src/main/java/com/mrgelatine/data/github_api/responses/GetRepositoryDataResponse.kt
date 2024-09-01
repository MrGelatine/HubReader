package com.mrgelatine.data.github_api.responses

import com.google.gson.annotations.SerializedName

data class DataInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("sha")
    val sha: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("git_url")
    val gitUrl: String,
    @SerializedName("download_url")
    val downloadUrl: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("_links")
    val links: Links
){
    data class Links(
        @SerializedName("self")
        val self: String,
        @SerializedName("git")
        val git: String,
        @SerializedName("html")
        val html: String
    )
}