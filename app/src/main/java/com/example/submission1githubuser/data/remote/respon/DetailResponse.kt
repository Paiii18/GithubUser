package com.example.submission1githubuser.data.remote.respon

import com.example.submission1githubuser.data.database.User
import com.google.gson.annotations.SerializedName

data class DetailResponse(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("followers")
    val followers: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("username")
    val username: Any? = null,

    @field:SerializedName("public_repos")
    val publicRepos: Int? = null,
    )
