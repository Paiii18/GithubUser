package com.example.submission2githubuser.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * from User ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE id = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<User>
}