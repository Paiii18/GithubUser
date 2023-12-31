package com.example.submission2githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission2githubuser.data.database.User
import com.example.submission2githubuser.data.repository.UserRepository

class MainViewModel (application: Application) : ViewModel(){
    private val mUserRepository :UserRepository = UserRepository(application)

    fun getAllFavorite(): LiveData<List<User>> = mUserRepository.getAllFavorite()
}
