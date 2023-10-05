package com.example.submission2githubuser.ui.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.submission1githubuser.R
import com.example.submission2githubuser.ui.viewmodel.ThemeViewModel
import com.example.submission2githubuser.ui.viewmodel.ViewModelFactory
import com.example.submission2githubuser.utils.SettingPreferences.SettingPreferences
import com.example.submission2githubuser.utils.SettingPreferences.dataStore


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )
        mainViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }


        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.visibility = View.GONE
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }

    companion object {
        var SPLASH_TIME_OUT: Long = 2000
    }
}