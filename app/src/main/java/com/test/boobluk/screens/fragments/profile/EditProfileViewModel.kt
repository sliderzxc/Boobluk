package com.test.boobluk.screens.fragments.profile

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.firebase.utils.profile.EditProfileFirebaseHelper
import com.test.boobluk.screens.fragments.chat.ChatFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.image.ImageHelper
import com.test.boobluk.utils.navigation.changeFragment
import com.test.boobluk.utils.toast.showDarkMotionSuccessColorToast
import java.io.ByteArrayOutputStream

class EditProfileViewModel(
    val editProfileFirebaseHelper: EditProfileFirebaseHelper,
    val imageHelper: ImageHelper
) : ViewModel() {

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
        binding: FragmentEditProfileBinding
    ) {
        editProfileFirebaseHelper.saveAllChanges(
            firebase = firebase,
            binding = binding
        )
    }

    fun bottomNavigationViewClickListener(binding: FragmentEditProfileBinding, parentFragmentManager: FragmentManager) {
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
                        binding = binding
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