package com.example.submission1githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1githubuser.adapter.SearchAdapter
import com.example.submission1githubuser.data.respon.ItemsItem

import com.example.submission1githubuser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        internal const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val layoutManager = LinearLayoutManager(this)
        binding.rvHome.layoutManager = layoutManager
       val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHome.addItemDecoration(itemDecoration)


        mainViewModel.listUser.observe(this) { consumerReviews ->
            setReviewData(consumerReviews)
        }
        mainViewModel.listLoading.observe(this) {
            showLoading(it)
        }
    }



    private fun setReviewData(consumerReviews: List<ItemsItem>) {
        val adapter = SearchAdapter()
        adapter.submitList(consumerReviews)
        binding.rvHome.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
