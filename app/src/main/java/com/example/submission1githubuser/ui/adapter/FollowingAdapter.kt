package com.example.submission1githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1githubuser.databinding.ItemUserBinding
import com.example.submission1githubuser.ui.respon.FollowersResponseItem

class FollowingAdapter :
    ListAdapter<FollowersResponseItem, FollowingAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(following: FollowersResponseItem) {
            binding.tvName.text = following.login
            binding.tvUrl.text = following.url
            Glide.with(binding.root.context)
                .load(following.avatarUrl)
                .into(binding.ivImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val follower = getItem(position)
        holder.bind(follower)
    }

    companion object {
        const val TAG = "FollowersAdapter"
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowersResponseItem>() {
            override fun areItemsTheSame(
                oldItem: FollowersResponseItem,
                newItem: FollowersResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FollowersResponseItem,
                newItem: FollowersResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}