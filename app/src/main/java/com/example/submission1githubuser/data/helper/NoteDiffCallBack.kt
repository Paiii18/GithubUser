package com.example.submission1githubuser.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.submission1githubuser.data.database.User

class FavoriteDiffCallback(
    private val oldUserList: List<User>,
    private val newUserList: List<User>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldUserList.size
    override fun getNewListSize(): Int = newUserList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUserList[oldItemPosition].id == newUserList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = oldUserList[oldItemPosition]
        val newFavorite = newUserList[newItemPosition]
        return oldFavorite.username == newFavorite.username && oldFavorite.avatarUrl == newFavorite.avatarUrl
    }
}