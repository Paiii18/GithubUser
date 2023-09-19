package com.example.submission1githubuser.data.retrofit


import com.example.submission1githubuser.data.respon.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchUsers(
        @Query("q") username: String
    ): Call<SearchResponse>
}