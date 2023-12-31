package com.example.submission2githubuser.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submission1githubuser.R
import com.example.submission1githubuser.databinding.ActivityDetailBinding
import com.example.submission2githubuser.data.database.User
import com.example.submission2githubuser.data.remote.respon.DetailResponse
import com.example.submission2githubuser.ui.adapter.SectionsPagerAdapter
import com.example.submission2githubuser.ui.viewmodel.APIViewModel
import com.example.submission2githubuser.ui.viewmodel.UserAddDeleteViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var isFavorite = false

    private val userAddDeleteViewModel by lazy {
        UserAddDeleteViewModel(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(EXTRA_NAME)

        val APIViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(APIViewModel::class.java)
        Log.i("FavoriteActivity", "onItemClick: detail ${name} ")

        APIViewModel.findDetail("$name")

        APIViewModel.detailUser.observe(this) { userDetail ->
            setUser(userDetail)
            userAddDeleteViewModel.checkUser("${userDetail.id}").observe(this){
                note ->
                isFavorite = note != null

                Log.i("DetailFragment", "onResponse: Note $isFavorite ")

                if (isFavorite) {
                    binding.fabAdd.setImageResource(R.drawable.ic_favorite)

                } else {
                    binding.fabAdd.setImageResource(R.drawable.ic_favorite_border)


                }
            }

           binding.fabAdd.setOnClickListener {

                val githubUser = User(
                    id = userDetail.id!!,
                    name = userDetail.login,
                    username = userDetail.htmlUrl,
                    avatarUrl = userDetail.avatarUrl
                )




                Log.i("DetailFragment", "onResponse: $isFavorite n ${userDetail.id} ")

                if (isFavorite) {
                    userAddDeleteViewModel.delete(githubUser)
                    binding.fabAdd.setImageResource(R.drawable.ic_favorite_border)
                    showToast(getString(R.string.delete))
                } else {
                    userAddDeleteViewModel.insert(githubUser)
                    binding.fabAdd.setImageResource(R.drawable.ic_favorite)
                    showToast(getString(R.string.added))
                }
            }
        }

        APIViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        val bundle = Bundle()
        bundle.putString(ARG_USERNAME, name)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)

        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()





    }



    private fun setUser(user: DetailResponse) {
        binding.apply {
            tvName.text = "${user.name}"
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
