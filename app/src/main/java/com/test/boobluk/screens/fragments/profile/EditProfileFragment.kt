package com.test.boobluk.screens.fragments.profile

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.firebase.database.getCurrentUserFieldsForFragmentEditProfile
import com.test.boobluk.screens.fragments.chat.ChatFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.navigation.changeFragment
import com.test.boobluk.utils.navigation.goToAddNewFragment
import com.test.boobluk.utils.toast.showDarkMotionInfoColorToast
import com.test.boobluk.utils.toast.showDarkMotionSuccessColorToast
import javax.inject.Inject

class EditProfileFragment : Fragment() {
    private val binding by lazy { FragmentEditProfileBinding.inflate(layoutInflater) }
    @Inject
    lateinit var editProfileViewModelFactory: EditProfileViewModelFactory
    private val editProfileViewModel: EditProfileViewModel by activityViewModels { editProfileViewModelFactory }
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
        saveChangesClickListenerAndSaveChanges()
    }

    private fun initConfig() {
        binding.mainBottomNavigationView.background = null
        getCurrentUserFieldsForFragmentEditProfile(
            firebase = firebase,
            binding = binding,
            context = requireContext()
        )
    }

    private fun saveChangesClickListenerAndSaveChanges() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.saveChanges -> {
                    editProfileViewModel.saveAllChanges(
                        firebase = firebase,
                        binding = binding
                    )
                    showDarkMotionSuccessColorToast(
                        fragment = this,
                        getString(R.string.all_changes_saved)
                    )
                }
            }
            true
        }
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun buttonAddNewChatClickListener() {
        binding.itemAddChatNavigation.setOnClickListener {
            goToAddNewFragment()
        }
    }

    private fun bottomNavigationViewClickListener() {
        binding.mainBottomNavigationView.menu.getItem(3).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemChatsNavigation -> {
                    parentFragmentManager.changeFragment(ChatFragment())
                }
                R.id.itemSearchNavigation -> {
                    parentFragmentManager.changeFragment(SearchFragment())
                }
                R.id.itemSettingsNavigation -> {
                    parentFragmentManager.changeFragment(SettingsFragment())
                }
                else -> true
            }
        }
    }

}