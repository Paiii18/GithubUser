package com.example.submission2githubuser.data.remote.retrofit


import com.example.submission2githubuser.data.remote.respon.DetailResponse
import com.example.submission2githubuser.data.remote.respon.FollowersResponseItem
import com.example.submission2githubuser.data.remote.respon.SearchResponse
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