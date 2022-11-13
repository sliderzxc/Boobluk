package com.test.boobluk.screens.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentSettingsBinding
import com.test.boobluk.screens.fragments.chat.ChatFragment
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.utils.navigation.changeFragment
import com.test.boobluk.utils.navigation.goToAddNewFragment
import com.test.boobluk.utils.navigation.goToLoginFragment

class SettingsFragment : Fragment() {
    private val binding by lazy { FragmentSettingsBinding.inflate(layoutInflater) }
    private val firebase = Firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        inject()
        initConfig()
        bottomNavigationViewClickListener()
        buttonAddNewChatClickListener()
        signOutClickListener()
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun initConfig() {
        binding.mainBottomNavigationView.background = null
    }

    private fun buttonAddNewChatClickListener() {
        binding.itemAddChatNavigation.setOnClickListener {
            goToAddNewFragment()
        }
    }

    private fun signOutClickListener() {
        binding.buttonSignOut.setOnClickListener {
            firebase.auth.signOut()
        }
    }

    private fun bottomNavigationViewClickListener() {
        binding.mainBottomNavigationView.menu.getItem(4).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemChatsNavigation -> {
                    parentFragmentManager.changeFragment(ChatFragment())
                }
                R.id.itemSearchNavigation -> {
                    parentFragmentManager.changeFragment(SearchFragment())
                }
                R.id.itemEditProfileNavigation -> {
                    parentFragmentManager.changeFragment(EditProfileFragment())
                }
                else -> true
            }
        }
    }
}
