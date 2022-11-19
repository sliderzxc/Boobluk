package com.test.boobluk.screens.fragments.search

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentChatBinding
import com.test.boobluk.databinding.FragmentSearchBinding
import com.test.boobluk.firebase.search.SearchFirebaseHelper
import com.test.boobluk.screens.fragments.chats.ListOfChatsFragment
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.navigation.changeFragment

class SearchViewModel(
    val searchFirebaseHelper: SearchFirebaseHelper
) : ViewModel() {

    fun bottomNavigationViewClickListener(
        binding: FragmentSearchBinding,
        parentFragmentManager: FragmentManager
    ) {
        binding.mainBottomNavigationView.menu.getItem(1).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemChatsNavigation -> { parentFragmentManager.changeFragment(
                    ListOfChatsFragment()
                ) }
                R.id.itemEditProfileNavigation -> { parentFragmentManager.changeFragment(
                    EditProfileFragment()
                ) }
                R.id.itemSettingsNavigation -> { parentFragmentManager.changeFragment(
                    SettingsFragment()
                ) }
                else -> true
            }
        }
    }

}