package com.test.boobluk.screens.fragments.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.firebase.utils.profile.EditProfileFirebaseHelper

class EditProfileViewModel(
    val editProfileFirebaseHelper: EditProfileFirebaseHelper
) : ViewModel() {

    fun saveAllChanges(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        editProfileFirebaseHelper.saveAllChanges(
            firebase = firebase,
            binding = binding
        )
    }

}