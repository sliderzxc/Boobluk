package com.test.boobluk.interfaces.profile

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentEditProfileBinding

interface EditProfileFirebaseInterface {

    fun saveAllChanges(
        firebase: Firebase,
        binding: FragmentEditProfileBinding,
        fragment: Fragment
    )

    fun getCurrentUserFieldsForFragmentEditProfile(
        firebase: Firebase,
        binding: FragmentEditProfileBinding,
        context: Context
    )

}