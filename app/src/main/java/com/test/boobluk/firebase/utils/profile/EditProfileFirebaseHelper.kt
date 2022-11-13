package com.test.boobluk.firebase.utils.profile

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO
import com.test.boobluk.utils.gender.Gender

class EditProfileFirebaseHelper {

    fun saveAllChanges(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        checkIfBioChangedAndSaveIfChanged(firebase, binding)
        checkIfGenderChangedAndSaveIfChanged(firebase, binding)
        checkIfUsernameChangedAndSaveIfChanged(firebase, binding)
    }

    private fun checkIfBioChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val currentTextBio = binding.etBio.text.toString()
        firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener {
            val oldTextBio = it.get("bio")
            if (currentTextBio == oldTextBio) {
                return@addOnSuccessListener
            }
            if (currentTextBio.isEmpty()) {
                return@addOnSuccessListener
            }
            if (currentTextBio != oldTextBio) {
                firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener { userInfo ->
                    val oldUser = userInfo.toObject<UserInfo>()!!
                    val currentUser = oldUser.copy(bio = currentTextBio)
                    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).set(currentUser)
                }
            }
        }
    }

    private fun checkIfGenderChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val gender = binding.rgChooseGender.checkedRadioButtonId
        if (gender == R.id.btnMale) {
            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener { userInfo ->
                val oldUser = userInfo.toObject<UserInfo>()!!
                val currentUser = oldUser.copy(gender = Gender.Male)
                firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).set(currentUser)
            }
        } else if (gender == R.id.btnFemale) {
            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener { userInfo ->
                val oldUser = userInfo.toObject<UserInfo>()!!
                val currentUser = oldUser.copy(gender = Gender.Female)
                firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).set(currentUser)
            }
        }

    }

    private fun checkIfUsernameChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val currentUsername = binding.etUsername.text.toString()
        firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener {
            val oldUsername = it.get("username")
            if (currentUsername == oldUsername) {
                return@addOnSuccessListener
            }
            if (currentUsername.isEmpty()) {
                return@addOnSuccessListener
            }
            if (currentUsername != oldUsername) {
                firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener { userInfo ->
                    val oldUser = userInfo.toObject<UserInfo>()!!
                    val currentUser = oldUser.copy(username = currentUsername)
                    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).set(currentUser)
                }
            }
        }
    }

}