package com.test.boobluk.screens.fragments.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentSettingsBinding
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.navigation.goToAddNewFragment
import com.test.boobluk.utils.preferences.addNewItemToPreferencesNotificationArray
import com.test.boobluk.utils.preferences.getArrayFromPreferencesNotificationArray
import javax.inject.Inject

class SettingsFragment : Fragment() {
    private val binding by lazy { FragmentSettingsBinding.inflate(layoutInflater) }
    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory
    private val settingsViewModel: SettingsViewModel by activityViewModels { settingsViewModelFactory }
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
        binding.button.setOnClickListener {

        }
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
        settingsViewModel.bottomNavigationViewClickListener(
            binding = binding,
            parentFragmentManager = parentFragmentManager
        )
    }
}
