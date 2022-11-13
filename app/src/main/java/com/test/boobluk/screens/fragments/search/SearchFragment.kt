package com.test.boobluk.screens.fragments.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.boobluk.R
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentSearchBinding
import com.test.boobluk.screens.fragments.chat.ChatFragment
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.navigation.changeFragment
import com.test.boobluk.utils.navigation.goToAddNewFragment

class SearchFragment : Fragment() {
    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }

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

    private fun bottomNavigationViewClickListener() {
        binding.mainBottomNavigationView.menu.getItem(1).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemChatsNavigation -> {
                    parentFragmentManager.changeFragment(ChatFragment())
                }
                R.id.itemEditProfileNavigation -> {
                    parentFragmentManager.changeFragment(EditProfileFragment())
                }
                R.id.itemSettingsNavigation -> {
                    parentFragmentManager.changeFragment(SettingsFragment())
                }
                else -> true
            }
        }
    }
}