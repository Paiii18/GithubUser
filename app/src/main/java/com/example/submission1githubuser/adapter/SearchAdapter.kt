package com.example.submission1githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1githubuser.data.respon.ItemsItem
import com.example.submission1githubuser.databinding.ItemUserBinding

class SearchAdapter : ListAdapter<ItemsItem, SearchAdapter.MyViewHolder>(DIFF_CALLBACK){

    class MyViewHolder (private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(review:ItemsItem) {
                binding.tvName.text = review.login
                binding.tvUrl.text = review.url
                Glide.with(binding.root.context)
                    .load(review.avatarUrl)
                    .into(binding.ivImage)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.MyViewHolder, position: Int) {
       val review = getItem(position)
        holder.bind(review)
    }
    companion object {
        const val TAG = "UserAdapter"
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

