package com.example.submission1githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1githubuser.data.respon.ItemsItem
import com.example.submission1githubuser.data.respon.SearchResponse
import com.example.submission1githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val listLoading: LiveData<Boolean> = _isLoading


    companion object {
        private const val TAG = "MainViewModel"
    }

init {
    findSearch()
}

     fun findSearch() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchUsers("Pai")
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

}