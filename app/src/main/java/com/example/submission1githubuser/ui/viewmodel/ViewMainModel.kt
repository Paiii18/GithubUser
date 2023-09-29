package com.example.submission1githubuser.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.submission1githubuser.utils.SettingPreferences.SettingPreferences
import kotlinx.coroutines.launch

class ViewMainModel(private val settingPreferences: SettingPreferences) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean>{
        return settingPreferences.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingPreferences.saveThemeSetting(isDarkModeActive)
        }
    }
}