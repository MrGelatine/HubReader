package com.mrgelatine.data.github_api.responses

import com.google.gson.annotations.SerializedName

data class SearchUserResponse (
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_result")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: List<GitHubSearchUser>)
{
    data class GitHubSearchUser(
        @SerializedName("login")
        val login: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("node_id")
        val nodeId: String,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("gravatar_id")
        val gravatarId: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("html_url")
        val htmlUrl: String,
        @SerializedName("followers_url")
        val followersUrl: String,
        @SerializedName("subscription_url")
        val subscriptionUrl: String,
        @SerializedName("organization_url")
        val organizationUrl: String,
        @SerializedName("repos_url")
        val reposUrl: String,
        @SerializedName("received_events_url")
        val receivedEventsUrl: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("score")
        val score: Int,
        @SerializedName("following_url")
        val followingUrl: String,
        @SerializedName("gists_url")
        val gistsUrl: String,
        @SerializedName("starred_url")
        val starredUrl: String,
        @SerializedName("events_url")
        val eventsUrl: String,
        @SerializedName("site_admin")
        val siteAdmin: Boolean
    )
}