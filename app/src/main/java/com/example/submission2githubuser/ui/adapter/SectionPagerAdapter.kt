package com.example.submission2githubuser.ui.adapter

import android.os.Bundle
import com.example.submission2githubuser.ui.Fragment.FollowingFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission2githubuser.ui.Fragment.FollowerFragment
import com.example.submission2githubuser.ui.activity.DetailActivity.Companion.ARG_USERNAME


class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username: String? = "name"

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = Bundle().apply {
            putString(FollowerFragment.EXTRA_NAME, username)
            putString(FollowingFragment.EXTRA_NAME, username)
        }
        return fragment as Fragment
    }

}