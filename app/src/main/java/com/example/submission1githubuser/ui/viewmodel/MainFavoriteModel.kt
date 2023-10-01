package com.example.submission1githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission1githubuser.data.database.Favorite
import com.example.submission1githubuser.data.repository.FavoriteRepository

class MainFavoriteModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorite()
}