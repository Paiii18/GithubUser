package com.example.submission1githubuser.data.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.submission1githubuser.data.database.Favorite
import com.example.submission1githubuser.data.repository.FavoriteRepository

class FavoriteAddUpdateViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)
    fun insert(favorite: Favorite) {
        mFavoriteRepository.insert(favorite)
    }

    fun update(favorite: Favorite) {
        mFavoriteRepository.update(favorite)
    }

    fun delete(favorite: Favorite) {
        mFavoriteRepository.delete(favorite)
    }
}