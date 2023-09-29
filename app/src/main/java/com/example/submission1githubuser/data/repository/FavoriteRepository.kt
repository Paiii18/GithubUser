package com.example.submission1githubuser.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.submission1githubuser.data.database.Favorite
import com.example.submission1githubuser.data.database.FavoriteDao
import com.example.submission1githubuser.data.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mNotesDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mNotesDao = db.FavoriteDao()
    }
    fun getAllFavorite(): LiveData<List<Favorite>> = mNotesDao.getAllFavorite()
    fun insert(favorite: Favorite) {
        executorService.execute { mNotesDao.insert(favorite) }
    }
    fun delete(favorite: Favorite) {
        executorService.execute { mNotesDao.delete(favorite) }
    }
    fun update(favorite: Favorite) {
        executorService.execute { mNotesDao.update(favorite) }
    }
}