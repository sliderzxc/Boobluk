package com.test.boobluk.firebase.database

import android.content.Context
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.test.boobluk.R
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.utils.binding.hideEditProfileFragmentDesignAndShowProgressBar
import com.test.boobluk.utils.binding.hideProgressBarAndShowProfileFragmentDesign
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO
import com.test.boobluk.utils.gender.Gender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun registerAndAddUserToDatabase(
    firebase: Firebase,
    binding: FragmentRegisterBinding
) {
    CoroutineScope(Dispatchers.IO).launch {
        val uid = firebase.auth.currentUser?.uid.toString()
        val email = binding.etEmail.text.toString()
        val username = binding.etUsername.text.toString()

        firebase.storage.getReferenceFromUrl(Constants.REFERENCES_NEW_USER_AVATAR).downloadUrl.addOnSuccessListener { uri ->
            val avatar = uri.toString()
            val userInfo = UserInfo(
                email = email,
                isEmailConfirmed = false,
                uid = uid,
                username = username,
                avatar = avatar,
                gender = null,
                bio = null
            )
            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).set(userInfo)
        }
    }
}

fun loginAndUpdateUserInDatabase(
    firebase: Firebase,
    binding: FragmentLoginBinding
) {
    CoroutineScope(Dispatchers.IO).launch {
        val uid = firebase.auth.currentUser?.uid.toString()
        val email = binding.etEmail.text.toString()
        val username = binding.etUsername.text.toString()

        firebase.storage.getReferenceFromUrl(Constants.REFERENCES_NEW_USER_AVATAR).downloadUrl.addOnSuccessListener { uri ->
            val avatar = uri.toString()
            val userInfo = UserInfo(
                email = email,
                isEmailConfirmed = true,
                uid = uid,
                username = username,
                avatar = avatar,
                gender = null,
                bio = null
            )
            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).set(userInfo)
        }
    }
}

fun getCurrentUserFieldsForFragmentEditProfile(
    firebase: Firebase,
    binding: FragmentEditProfileBinding,
    context: Context
) {
    hideEditProfileFragmentDesignAndShowProgressBar(binding = binding)
    val uid = firebase.auth.currentUser?.uid.toString()

    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener {
        val user = it.toObject<UserInfo>()
        binding.etUsername.setText(user?.username ?: "")
        binding.etEmail.setText(user?.email ?: "")
        binding.etBio.setText(user?.bio ?: "")
        if (user?.gender?.toString() == Gender.Male.name) {
            binding.rgChooseGender.check(R.id.btnMale)
        } else if (user?.gender?.toString() == Gender.Female.name) {
            binding.rgChooseGender.check(R.id.btnFemale)
        } else { binding.rgChooseGender.check(0) }
        Glide.with(context).load(user?.avatar).into(binding.ivUserAvatar)
        hideProgressBarAndShowProfileFragmentDesign(binding = binding)
    }
}