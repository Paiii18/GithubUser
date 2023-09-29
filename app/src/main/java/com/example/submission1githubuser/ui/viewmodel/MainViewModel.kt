package com.example.submission1githubuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1githubuser.data.remote.respon.DetailResponse
import com.example.submission1githubuser.data.remote.respon.FollowersResponseItem
import com.example.submission1githubuser.data.remote.respon.ItemsItem
import com.example.submission1githubuser.data.remote.respon.SearchResponse
import com.example.submission1githubuser.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _detailUser = MutableLiveData<DetailResponse>()
    val detailUser: LiveData<DetailResponse> = _detailUser

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listFollower = MutableLiveData<List<FollowersResponseItem>>()
    val listReviewFollower: LiveData<List<FollowersResponseItem>> = _listFollower

    private val _listFollowing  = MutableLiveData<List<FollowersResponseItem>>()
    val listReviewFollowing: LiveData<List<FollowersResponseItem>> = _listFollowing


    companion object {
        private const val TAG = "MainViewModel"
    }

init {
    findSearch()
}

    fun findDetail(username: String) {
        _isLoading.value = true
        var client = ApiConfig.getApiService().getUser(username)
        client.enqueue(object :Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful){
                    _detailUser.postValue(response.body())
                }else{
                    Log.e(TAG,"onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable){
                _isLoading.value = false
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }

     fun findSearch(username : String = "Paiii") {
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchUsers(username)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val searchResponse = response.body()
                    val itemList = searchResponse?.items?: emptyList()
                    _listUser.postValue(itemList as List<ItemsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun fetchDataFollowers(username: String) {
        _isLoading.postValue(true) // Set isLoading to true while fetching data

        val apiService = ApiConfig.getApiService()
        val call = apiService.getFollowers(username)

        call.enqueue(object : Callback<List<FollowersResponseItem>> { // Use List<ItemFollowers> as the callback type
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val followersList = response.body() ?: emptyList()
                    _listFollower.postValue(followersList)
                    Log.i(TAG, "Fetch followers successful. Count: ${followersList.size}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

                _isLoading.postValue(false) // Set isLoading to false after fetching data
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                Log.e(TAG, "Fetch followers failed. Error: ${t.message}")

                // Handle network error here
                _isLoading.postValue(false) // Set isLoading to false if there's an error
            }
        })
    } fun fetchDataFollowing(username: String) {
        _isLoading.postValue(true) // Set isLoading to true while fetching data

        val apiService = ApiConfig.getApiService()
        val call = apiService.getFollowing(username)

        call.enqueue(object : Callback<List<FollowersResponseItem>> { // Use List<ItemFollowers> as the callback type
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val followingList = response.body() ?: emptyList()
                    _listFollowing.postValue(followingList)
                    Log.i(TAG, "Fetch followers successful. Count: ${followingList.size}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

                _isLoading.postValue(false) // Set isLoading to false after fetching data
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                Log.e(TAG, "Fetch followers failed. Error: ${t.message}")

                // Handle network error here
                _isLoading.postValue(false) // Set isLoading to false if there's an error
            }
        })
    }

}