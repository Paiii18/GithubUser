package com.example.submission1githubuser.ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1githubuser.databinding.FragmentFollowingBinding
import com.example.submission1githubuser.ui.activity.DetailActivity
import com.example.submission1githubuser.ui.adapter.FollowersAdapter
import com.example.submission1githubuser.ui.respon.FollowersResponseItem
import com.example.submission1githubuser.ui.viewmodel.MainViewModel


class FollowingFragment ( val username :String): Fragment(), FollowersAdapter.OnItemClickListener {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.i("com.example.submission1githubuser.ui.Fragment.FollowingFragment", "onViewCreated: $username")
        mainViewModel.fetchDataFollowing(username)
        val layoutManager = LinearLayoutManager(context)

        binding.rvFollowing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)

        mainViewModel.listReviewFollowing.observe(viewLifecycleOwner) { consumerReviews ->
            setReviewData(consumerReviews)
        }

        mainViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun setReviewData(consumerReviews: List<FollowersResponseItem>) {
        val adapter = FollowersAdapter(this)
        adapter.submitList(consumerReviews)
        binding.rvFollowing.adapter = adapter

        if (consumerReviews.isEmpty()) {
            binding.emptyDataText.visibility = View.VISIBLE
        } else {
            binding.emptyDataText.visibility = View.GONE
        }

    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: FollowersResponseItem) {
        val username = item.login
        val moveWithDataIntent = Intent(context, DetailActivity::class.java)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_NAME, username)
        startActivity(moveWithDataIntent)
    }
}