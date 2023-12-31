package com.example.submission2githubuser.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1githubuser.R
import com.example.submission1githubuser.databinding.ActivityFavoriteBinding
import com.example.submission2githubuser.data.database.User
import com.example.submission2githubuser.ui.adapter.UserAdapter
import com.example.submission2githubuser.ui.viewmodel.FavoriteModelFactory
import com.example.submission2githubuser.ui.viewmodel.MainViewModel


class FavoriteActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {
    private var _activityMainBinding: ActivityFavoriteBinding? = null
    private val binding get() = _activityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorite)


        _activityMainBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val mainViewModel = obtainViewModel(this@FavoriteActivity)
        mainViewModel.getAllFavorite().observe(this) { noteList ->

            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        }
        adapter = UserAdapter(this)
        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter

    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    override fun onItemClick(item: User) {

        val username = item.name
        Log.i("FavoriteActivity", "onItemClick:${username} ")
        val moveWithDataIntent = Intent(this@FavoriteActivity, DetailActivity::class.java)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_NAME, username)
        startActivity(moveWithDataIntent)
    }
}