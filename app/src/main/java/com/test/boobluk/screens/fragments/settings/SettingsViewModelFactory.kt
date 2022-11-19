package com.test.boobluk.screens.fragments.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.search.SearchFirebaseHelper
import com.test.boobluk.firebase.settings.SettingsFirebaseHelper
import javax.inject.Inject

class SettingsViewModelFactory @Inject constructor(
    val settingsFirebaseHelper: SettingsFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(
            settingFirebaseHelper = settingsFirebaseHelper
        ) as T
    }
}