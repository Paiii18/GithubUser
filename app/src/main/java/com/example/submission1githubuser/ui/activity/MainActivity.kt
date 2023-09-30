package com.example.submission1githubuser.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1githubuser.R
import com.example.submission1githubuser.ui.viewmodel.MainViewModel
import com.example.submission1githubuser.ui.adapter.SearchAdapter
import com.example.submission1githubuser.data.remote.respon.ItemsItem
import com.example.submission1githubuser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),
    SearchAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    companion object {
        internal const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.show()
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        binding.rvHome.layoutManager = layoutManager

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    val query = searchView.text.toString().trim()
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        if (query.isEmpty()) {
                            // Jika pengguna tidak memasukkan teks, panggil metode fetchDataFromApi dengan nilai default "sahril"
                            mainViewModel.findSearch()
                        } else {
                            // Jika pengguna memasukkan teks, panggil metode fetchDataFromApi dengan nilai query
                            mainViewModel.findSearch(query)
                        }

                        // Lakukan tindakan lain yang Anda perlukan saat pengguna menekan tombol "Cari"
                        searchBar.text = searchView.text
                        searchView.hide()
                        Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT)
                            .show()

                        true
                    } else {
                        false
                    }
                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHome.addItemDecoration(itemDecoration)

        mainViewModel.listUser.observe(this) { consumerReviews ->
            setReviewData(consumerReviews)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }


    }

    private fun setReviewData(consumerReviews: List<ItemsItem>) {
        val adapter = SearchAdapter(this)
        adapter.submitList(consumerReviews)
        binding.rvHome.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onItemClick(item: ItemsItem) {
        val username = item.login
        val moveWithDataIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_NAME, username)
        startActivity(moveWithDataIntent)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                Log.i("MainActivity", "onOptionsItemSelected: Setting")
                val intent = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.menu2 -> {
                Log.i("MainActivity", "onOptionsItemSelected: Favo")

                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }



}
