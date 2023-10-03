package com.example.submission1githubuser.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.submission1githubuser.data.database.User
import com.example.submission1githubuser.data.database.UserDao
import com.example.submission1githubuser.data.database.UserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {
    private val mFavorite: UserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserRoomDatabase.getDatabase(application)
        mFavorite = db.FavoriteDao()
    }

    fun getAllFavorite(): LiveData<List<User>> = mFavorite.getAllFavorite()
    fun insert(user: User) {
        executorService.execute { mFavorite.insert(user) }
    }

    fun delete(user: User) {
        executorService.execute { mFavorite.delete(user) }
    }

    fun update(user: User) {
        executorService.execute { mFavorite.update(user) }
    }

    fun checkUser(username: String): LiveData<User> {
        return mFavorite.getFavoriteUserByUsername(username)
    }
}