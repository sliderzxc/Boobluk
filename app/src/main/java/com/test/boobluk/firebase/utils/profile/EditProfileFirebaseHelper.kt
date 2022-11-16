package com.test.boobluk.firebase.utils.profile

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.test.boobluk.R
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.utils.constants.Constants.REFERENCE_PACKAGE_USER_AVATARS
import com.test.boobluk.utils.constants.Constants.REFERENCE_BIO
import com.test.boobluk.utils.constants.Constants.REFERENCE_USERNAME
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO
import com.test.boobluk.utils.gender.Gender
import java.io.ByteArrayOutputStream

class EditProfileFirebaseHelper {

    fun saveAllChanges(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        checkIfBioChangedAndSaveIfChanged(firebase, binding)
        checkIfGenderChangedAndSaveIfChanged(firebase, binding)
        checkIfUsernameChangedAndSaveIfChanged(firebase, binding)
        checkIfPasswordChangedAndSaveIfChanged(firebase, binding)
        checkIfAvatarChangedAndSaveIfChanged(firebase, binding)
    }

    private fun checkIfBioChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val currentTextBio = binding.etBio.text?.trim().toString()

        firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
            .addOnSuccessListener {
                val oldTextBio = it.get(REFERENCE_BIO)
                if (currentTextBio == oldTextBio) {
                    return@addOnSuccessListener
                }
                if (currentTextBio.isEmpty()) {
                    return@addOnSuccessListener
                }
                if (currentTextBio != oldTextBio) {
                    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
                        .addOnSuccessListener { userInfo ->
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
            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
                .addOnSuccessListener { userInfo ->
                    val oldUser = userInfo.toObject<UserInfo>()!!
                    val currentUser = oldUser.copy(gender = Gender.Male)
                    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid)
                        .set(currentUser)
                }
        } else if (gender == R.id.btnFemale) {
            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
                .addOnSuccessListener { userInfo ->
                    val oldUser = userInfo.toObject<UserInfo>()!!
                    val currentUser = oldUser.copy(gender = Gender.Female)
                    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid)
                        .set(currentUser)
                }
        }

    }

    private fun checkIfUsernameChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val currentUsername = binding.etUsername.text?.trim().toString()
        firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
            .addOnSuccessListener {
                val oldUsername = it.get(REFERENCE_USERNAME)
                if (currentUsername == oldUsername || currentUsername.isEmpty()) {
                    return@addOnSuccessListener
                }
                if (currentUsername != oldUsername) {
                    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
                        .addOnSuccessListener { userInfo ->
                            val oldUser = userInfo.toObject<UserInfo>()!!
                            val currentUser = oldUser.copy(username = currentUsername)
                            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid)
                                .set(currentUser)
                        }
                }
            }
    }

    private fun checkIfPasswordChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        val password = binding.etPassword.text?.trim().toString()

        if (password.isEmpty() || password.length < 6) {
            return
        }
        firebase.auth.currentUser?.updatePassword(password)
    }

    private fun checkIfAvatarChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val bitmap = (binding.ivUserAvatar.drawable).toBitmap()
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val userReference = firebase.storage.getReferenceFromUrl("$REFERENCE_PACKAGE_USER_AVATARS/$uid")
        userReference.putBytes(byteArray).continueWithTask { userReference.downloadUrl }.addOnCompleteListener {
            val imageUrl = it.result
            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
                .addOnSuccessListener { userInfo ->
                    val oldUser = userInfo.toObject<UserInfo>()!!
                    val currentUser = oldUser.copy(avatar = imageUrl.toString())
                    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).set(currentUser)
                }
        }
    }
}