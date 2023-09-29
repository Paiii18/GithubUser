package com.example.submission1githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1githubuser.databinding.ItemUserBinding
import com.example.submission1githubuser.data.remote.respon.FollowersResponseItem

class FollowersAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<FollowersResponseItem, FollowersAdapter.MyViewHolder>(DIFF_CALLBACK) {

    interface OnItemClickListener {
        fun onItemClick(item: FollowersResponseItem)
    }

    class MyViewHolder(
        private val binding: ItemUserBinding, private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: FollowersResponseItem) {
            binding.tvName.text = follower.login
            binding.tvUrl.text = follower.url
            Glide.with(binding.root.context)
                .load(follower.avatarUrl)
                .into(binding.ivImage)
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(follower)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowersAdapter.MyViewHolder(binding, onItemClickListener)
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