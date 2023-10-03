package com.example.submission1githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1githubuser.data.remote.respon.ItemsItem
import com.example.submission1githubuser.databinding.ItemUserBinding

class SearchAdapter (private val onItemClickListener: OnItemClickListener) : ListAdapter<ItemsItem, SearchAdapter.MyViewHolder>(DIFF_CALLBACK){

    interface OnItemClickListener {
        fun onItemClick(item: ItemsItem)
    }
    class MyViewHolder (private val binding: ItemUserBinding, private val onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(review: ItemsItem) {
                binding.tvName.text = review.login
                binding.tvUrl.text = review.htmlUrl
                Glide.with(binding.root.context)
                    .load(review.avatarUrl)
                    .into(binding.ivImage)

                itemView.setOnClickListener {
                    onItemClickListener.onItemClick(review)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding,onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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

