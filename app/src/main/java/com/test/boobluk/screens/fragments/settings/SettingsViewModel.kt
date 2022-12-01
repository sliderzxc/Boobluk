package com.test.boobluk.screens.fragments.settings

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentSettingsBinding
import com.test.boobluk.firebase.settings.SettingsFirebaseHelper
import com.test.boobluk.interfaces.settings.SettingsFirebaseInterface
import com.test.boobluk.screens.fragments.chats.ListOfChatsFragment
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.utils.navigation.changeFragment

class SettingsViewModel(
    val settingFirebaseInterface: SettingsFirebaseInterface
) : ViewModel() {

    fun bottomNavigationViewClickListener(
        binding: FragmentSettingsBinding,
        parentFragmentManager: FragmentManager
    ) {
        binding.mainBottomNavigationView.menu.getItem(4).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemChatsNavigation -> { parentFragmentManager.changeFragment(ListOfChatsFragment()) }
                R.id.itemSearchNavigation -> { parentFragmentManager.changeFragment(SearchFragment()) }
                R.id.itemEditProfileNavigation -> { parentFragmentManager.changeFragment(EditProfileFragment()) }
                else -> true
            }
        }
    }

}