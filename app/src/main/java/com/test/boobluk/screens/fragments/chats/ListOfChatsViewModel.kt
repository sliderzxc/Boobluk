package com.test.boobluk.screens.fragments.chats

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.adapter.ChatAdapter
import com.test.boobluk.databinding.FragmentListOfChatsBinding
import com.test.boobluk.firebase.chats.ListOfChatsFirebaseHelper
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.navigation.changeFragment

class ListOfChatsViewModel(
    val listOfChatsFirebaseHelper: ListOfChatsFirebaseHelper
) : ViewModel() {

    fun getAllChats(
        binding: FragmentListOfChatsBinding,
        firebase: Firebase,
        chatAdapter: ChatAdapter
    ) {
        listOfChatsFirebaseHelper.getAllChats(
            binding = binding,
            firebase = firebase,
            chatAdapter = chatAdapter
        )
    }

    fun bottomNavigationViewClickListener(binding: FragmentListOfChatsBinding, parentFragmentManager: FragmentManager) {
        binding.mainBottomNavigationView.menu.getItem(0).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemSearchNavigation -> { parentFragmentManager.changeFragment(SearchFragment()) }
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