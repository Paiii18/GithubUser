package com.example.submission1githubuser.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submission1githubuser.R
import com.example.submission1githubuser.databinding.ActivityDetailBinding
import com.example.submission1githubuser.ui.adapter.SectionsPagerAdapter
import com.example.submission1githubuser.data.remote.respon.DetailResponse
import com.example.submission1githubuser.ui.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val ARG_USERNAME = "username"
        const val EXTRA_NAME = "extra_name"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(EXTRA_NAME)

        val mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.findDetail("$name")
        mainViewModel.detailUser.observe(this) {userDetail ->
            setUser(userDetail)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, username = "$name"  )
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun setUser(user: DetailResponse) {
        binding.tvName.text = user.name
        binding.tvUsername.text = user.login
        binding.tvFollower.text = "${user.followers}"
        binding.tvFollowing.text = "${user.following}"
        binding.tvRepo.text = user.publicRepos.toString()
        Glide.with(this)
            .load(user.avatarUrl)
            .into(binding.imageView)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}