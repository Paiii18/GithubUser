package com.example.submission1githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1githubuser.data.database.User
import com.example.submission1githubuser.data.helper.FavoriteDiffCallback
import com.example.submission1githubuser.databinding.ItemUserBinding


class UserAdapter : RecyclerView.Adapter<UserAdapter.NoteViewHolder>() {
    private val listUser = ArrayList<User>()
    fun setListNotes(listGithubUser: List<User>) {
        val diffCallback = FavoriteDiffCallback(this.listUser, listGithubUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUser.clear()
        this.listUser.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listUser[position])
    }
    override fun getItemCount(): Int {
        return listUser.size
    }
    inner class NoteViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: User) {
            with(binding) {
                binding.tvName.text = "${review.username}"
                binding.tvUrl.text = review.avatarUrl
                Glide.with(binding.root.context)
                    .load(review.name)
                    .into(binding.ivImage)
            }
        }
    }
}