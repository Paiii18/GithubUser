package com.example.submission2githubuser.ui.adapter

import com.example.submission2githubuser.ui.Fragment.FollowingFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission2githubuser.ui.Fragment.FollowerFragment


class SectionsPagerAdapter(activity: AppCompatActivity,val username: String) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment(username)
            1 -> fragment = FollowingFragment(username)
        }
        return fragment as Fragment
    }

}