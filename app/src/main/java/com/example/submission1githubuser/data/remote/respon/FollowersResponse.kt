package com.example.submission1githubuser.data.remote.respon

import com.google.gson.annotations.SerializedName

data class FollowersResponse(

	@field:SerializedName("FollowersResponse")
	val followersResponse: List<FollowersResponseItem?>? = null
)

data class FollowersResponseItem(


	@field:SerializedName("repos_url")
	val reposUrl: String? = null,

	@field:SerializedName("following_url")
	val followingUrl: String? = null,


	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("followers_url")
	val followersUrl: String? = null,


	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,


	@field:SerializedName("id")
	val id: Int? = null,


)
