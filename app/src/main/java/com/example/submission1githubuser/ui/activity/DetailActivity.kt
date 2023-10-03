package com.example.submission1githubuser.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submission1githubuser.R
import com.example.submission1githubuser.data.remote.respon.DetailResponse
import com.example.submission1githubuser.databinding.ActivityDetailBinding
import com.example.submission1githubuser.ui.adapter.SectionsPagerAdapter
import com.example.submission1githubuser.ui.viewmodel.APIViewModel
import com.example.submission1githubuser.ui.viewmodel.UserAddDeleteViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var isFavorite = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(EXTRA_NAME)

        val APIViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(APIViewModel::class.java)
        APIViewModel.findDetail("$name")
        APIViewModel.detailUser.observe(this) { userDetail ->
            setUser(userDetail)
        }

        APIViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, username = "$name")
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


        val fabAdd = binding.fabAdd
        val userAddDeleteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserAddDeleteViewModel::class.java)

        userAddDeleteViewModel.checkUser("$name").observe(this) { user ->
            isFavorite = user != null
            updateFavoriteButton()
        }


    }



    private fun setUser(user: DetailResponse) {
        binding.apply {
            tvName.text = "${user.username}"
            tvUsername.text = user.login
            tvFollower.text = "${user.followers}"
            tvFollowing.text = "${user.following}"
            tvRepo.text = user.publicRepos.toString()
            Glide.with(this@DetailActivity)
                .load(user.avatarUrl)
                .into(imageView)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun updateFavoriteButton() {
        val fabAdd = binding.fabAdd
        if (isFavorite) {
            fabAdd.setImageResource(R.drawable.ic_favorite)
        } else {
            fabAdd.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_USERNAME = "username"
        const val EXTRA_NAME = "extra_name"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
