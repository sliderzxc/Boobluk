package com.test.boobluk.screens.fragments.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.settings.SettingsFirebaseHelper
import com.test.boobluk.interfaces.settings.SettingsFirebaseInterface
import javax.inject.Inject

class SettingsViewModelFactory @Inject constructor(
    val settingsFirebaseInterface: SettingsFirebaseInterface
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(
            settingFirebaseInterface = settingsFirebaseInterface
        ) as T
    }
}