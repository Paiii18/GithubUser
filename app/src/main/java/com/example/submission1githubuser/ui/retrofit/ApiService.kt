package com.example.submission1githubuser.ui.retrofit


import com.example.submission1githubuser.ui.respon.DetailResponse
import com.example.submission1githubuser.ui.respon.FollowersResponseItem
import com.example.submission1githubuser.ui.respon.ItemsItem
import com.example.submission1githubuser.ui.respon.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchUsers(
        @Query("q") username: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<FollowersResponseItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<FollowersResponseItem>>
}