package com.example.submission2githubuser.ui.Fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1githubuser.databinding.FragmentFollowerBinding
import com.example.submission2githubuser.ui.activity.DetailActivity
import com.example.submission2githubuser.ui.adapter.FollowersAdapter
import com.example.submission2githubuser.data.remote.respon.FollowersResponseItem
import com.example.submission2githubuser.ui.viewmodel.APIViewModel


class FollowerFragment( ) : Fragment(), FollowersAdapter.OnItemClickListener {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val APIViewModel = ViewModelProvider(this).get(APIViewModel::class.java)
        Log.i("com.example.submission1githubuser.ui.Fragment.FollowerFragment", "onViewCreated: $EXTRA_NAME")

        APIViewModel.fetchDataFollowers(EXTRA_NAME)
        val orientation = resources.configuration.orientation

        val layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(context, 2)


        } else {
            LinearLayoutManager(context)
        }
        binding.rvFollower.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollower.addItemDecoration(itemDecoration)

        APIViewModel.listReviewFollower.observe(viewLifecycleOwner) { consumerReviews ->
            setReviewData(consumerReviews)
        }

        APIViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun setReviewData(consumerReviews: List<FollowersResponseItem>) {
        val adapter = FollowersAdapter(this)
        adapter.submitList(consumerReviews)
        binding.rvFollower.adapter = adapter

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