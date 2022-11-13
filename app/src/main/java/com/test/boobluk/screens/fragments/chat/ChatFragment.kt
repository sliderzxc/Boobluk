package com.test.boobluk.screens.fragments.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.boobluk.R
import com.test.boobluk.adapter.ChatAdapter
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentChatBinding
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.navigation.changeFragment
import com.test.boobluk.utils.navigation.goToAddNewFragment

class ChatFragment : Fragment() {
    private val binding by lazy { FragmentChatBinding.inflate(layoutInflater) }

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
        initRecyclerViewChat()
    }

    private fun initConfig() {
        binding.mainBottomNavigationView.background = null
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun initRecyclerViewChat() {
        binding.rvChats.adapter = ChatAdapter()
    }

    private fun buttonAddNewChatClickListener() {
        binding.itemAddChatNavigation.setOnClickListener {
            goToAddNewFragment()
        }
    }

    private fun bottomNavigationViewClickListener() {
        binding.mainBottomNavigationView.menu.getItem(0).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemSearchNavigation -> {
                    parentFragmentManager.changeFragment(SearchFragment())
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