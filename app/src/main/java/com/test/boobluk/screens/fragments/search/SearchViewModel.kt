package com.test.boobluk.screens.fragments.search

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentSearchBinding
import com.test.boobluk.interfaces.search.SearchFirebaseInterface
import com.test.boobluk.screens.fragments.chats.ListOfChatsFragment
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.navigation.changeFragment

class SearchViewModel(
    val searchFirebaseInterface: SearchFirebaseInterface
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

    fun iniCardStackView(
        firebase: Firebase,
        context: Context,
        binding: FragmentSearchBinding
    ) {
        searchFirebaseInterface.initCardStackView(
            firebase = firebase,
            context = context,
            binding = binding
        )
    }

}