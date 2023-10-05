package com.example.submission2githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission2githubuser.data.database.User
import com.example.submission2githubuser.data.repository.UserRepository

class UserAddDeleteViewModel(application: Application) : ViewModel() {

    var mUserRepository: UserRepository = UserRepository(application)
    fun insert(user: User) {
        mUserRepository.insert(user)
    }

    fun update(user: User) {
        mUserRepository.update(user)
    }

    fun delete(user: User) {
        mUserRepository.delete(user)
    }

    fun checkUser(username: String): LiveData<User> {
        return mUserRepository.checkUser(username)
    }
}