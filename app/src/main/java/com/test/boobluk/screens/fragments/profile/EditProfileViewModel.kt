package com.test.boobluk.screens.fragments.profile

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.firebase.profile.EditProfileFirebaseHelper
import com.test.boobluk.screens.fragments.chats.ListOfChatsFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.image.ImageHelper
import com.test.boobluk.utils.navigation.changeFragment
import com.test.boobluk.utils.toast.showDarkMotionSuccessColorToast

class EditProfileViewModel(
    val editProfileFirebaseHelper: EditProfileFirebaseHelper,
    val imageHelper: ImageHelper
) : ViewModel() {

    fun getCurrentUserFieldsForFragmentEditProfile(firebase: Firebase, binding: FragmentEditProfileBinding, context: Context) {
        editProfileFirebaseHelper.getCurrentUserFieldsForFragmentEditProfile(
            firebase = firebase,
            binding = binding,
            context = context
        )
    }

    fun onClickChooseImageListener(
        binding: FragmentEditProfileBinding,
        launcher: ActivityResultLauncher<Intent>
    ) {
        binding.ivUserAvatar.setOnClickListener {
            imageHelper.onClickChooseImage(launcher)
        }
        binding.tvUploadNewAvatar.setOnClickListener {
            imageHelper.onClickChooseImage(launcher)
        }
    }

    private fun saveAllChanges(
        firebase: Firebase,
        binding: FragmentEditProfileBinding,
        fragment: Fragment
    ) {
        editProfileFirebaseHelper.saveAllChanges(
            firebase = firebase,
            binding = binding,
            fragment = fragment
        )
    }

    fun bottomNavigationViewClickListener(binding: FragmentEditProfileBinding, parentFragmentManager: FragmentManager) {
        binding.mainBottomNavigationView.menu.getItem(3).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemChatsNavigation -> {
                    parentFragmentManager.changeFragment(ListOfChatsFragment())
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

    fun doOnTextChanges(binding: FragmentEditProfileBinding) {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
        }
        binding.etUsername.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutUsername.error = null
            binding.textInputLayoutUsername.isErrorEnabled = false
        }
    }

    fun toolbarClickListenerAndSaveChanges(
        firebase: Firebase,
        binding: FragmentEditProfileBinding,
        fragment: Fragment
    ) {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.saveChanges -> {
                    saveAllChanges(
                        firebase = firebase,
                        binding = binding,
                        fragment = fragment
                    )
                    showDarkMotionSuccessColorToast(
                        fragment = fragment,
                        fragment.getString(R.string.all_changes_saved)
                    )
                }
            }
            true
        }
    }

}