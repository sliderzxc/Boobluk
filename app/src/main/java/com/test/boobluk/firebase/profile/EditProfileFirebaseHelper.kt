package com.test.boobluk.firebase.profile

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.test.boobluk.R
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.utils.binding.hideEditProfileFragmentDesignAndShowProgressBar
import com.test.boobluk.utils.binding.hideProgressBarAndShowProfileFragmentDesign
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.constants.Constants.REFERENCE_PACKAGE_USER_AVATARS
import com.test.boobluk.utils.constants.Constants.REFERENCE_BIO
import com.test.boobluk.utils.constants.Constants.REFERENCE_USERNAME
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO
import com.test.boobluk.utils.gender.Gender
import com.test.boobluk.utils.toast.showDarkMotionSuccessColorToast
import java.io.ByteArrayOutputStream

class EditProfileFirebaseHelper {

    fun saveAllChanges(
        firebase: Firebase,
        binding: FragmentEditProfileBinding,
        fragment: Fragment
    ) {
        checkIfBioChangedAndSaveIfChanged(firebase, binding)
        checkIfGenderChangedAndSaveIfChanged(firebase, binding)
        checkIfUsernameChangedAndSaveIfChanged(firebase, binding, fragment)
        checkIfPasswordChangedAndSaveIfChanged(firebase, binding)
        checkIfAvatarChangedAndSaveIfChanged(firebase, binding)
        checkIfEmailChangesAndSaveIfChanged(firebase, binding, fragment)
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
                            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid)
                                .set(currentUser)
                        }
                }
            }
    }

    private fun checkIfGenderChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding,
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
        binding: FragmentEditProfileBinding,
        fragment: Fragment
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val currentUsername = binding.etUsername.text?.trim().toString()
        firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
            .addOnSuccessListener {
                val oldUsername = it.get(REFERENCE_USERNAME)
                if (currentUsername == oldUsername) {
                    return@addOnSuccessListener
                }
                if (currentUsername.isEmpty()) {
                    binding.etUsername.error = fragment.getString(R.string.username_is_empty)
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
        binding: FragmentEditProfileBinding,
    ) {
        val password = binding.etPassword.text?.trim().toString()

        if (password.isEmpty() || password.length < 6) {
            return
        }
        firebase.auth.currentUser?.updatePassword(password)
    }

    private fun checkIfAvatarChangedAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding,
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val bitmap = (binding.ivUserAvatar.drawable).toBitmap()
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        firebase.storage.getReferenceFromUrl("$REFERENCE_PACKAGE_USER_AVATARS/$uid")
            .getBytes(10000000).addOnSuccessListener {
                if (it != null && !it.contentEquals(byteArray)) {
                    val userReference =
                        firebase.storage.getReferenceFromUrl("$REFERENCE_PACKAGE_USER_AVATARS/$uid")
                    userReference.putBytes(byteArray).continueWithTask { userReference.downloadUrl }
                        .addOnCompleteListener { task ->
                            val imageUrl = task.result
                            firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
                                .addOnSuccessListener { userInfo ->
                                    val oldUser = userInfo.toObject<UserInfo>()!!
                                    val currentUser = oldUser.copy(avatar = imageUrl.toString())
                                    firebase.firestore.collection(REFERENCE_USER_INFO).document(uid)
                                        .set(currentUser)
                                }
                        }
                }
            }
    }

    private fun checkIfEmailChangesAndSaveIfChanged(
        firebase: Firebase,
        binding: FragmentEditProfileBinding,
        fragment: Fragment
    ) {
        val email = binding.etEmail.text?.trim().toString()

        if (email.isEmpty()) {
            binding.etEmail.error = fragment.getString(R.string.email_is_empty)
            return
        }

        firebase.auth.currentUser?.updateEmail(email)?.addOnFailureListener {
            val exception = it.message.toString()

            if (exception == Constants.EMAIL_ADDRESS_IS_INCORRECT) {
                clearTextInputLayoutUsernameError(binding = binding)
                binding.textInputLayoutEmail.error = Constants.EMAIL_ADDRESS_IS_INCORRECT
                return@addOnFailureListener
            }
            if (exception == Constants.EMAIL_WAS_NOT_FOUND_INFO) {
                clearTextInputLayoutUsernameError(binding = binding)
                binding.textInputLayoutEmail.error = Constants.EMAIL_WAS_NOT_FOUND
                return@addOnFailureListener
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

        firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get()
            .addOnSuccessListener {
                val user = it.toObject<UserInfo>()
                binding.etUsername.setText(user?.username ?: "")
                binding.etEmail.setText(user?.email ?: "")
                binding.etBio.setText(user?.bio ?: "")
                if (user?.gender?.toString() == Gender.Male.name) {
                    binding.rgChooseGender.check(R.id.btnMale)
                } else if (user?.gender?.toString() == Gender.Female.name) {
                    binding.rgChooseGender.check(R.id.btnFemale)
                } else {
                    binding.rgChooseGender.check(0)
                }
                Glide.with(context).load(user?.avatar).into(binding.ivUserAvatar)
                hideProgressBarAndShowProfileFragmentDesign(binding = binding)
            }
    }

    private fun clearTextInputLayoutUsernameError(binding: FragmentEditProfileBinding) {
        binding.textInputLayoutUsername.error = null
        binding.textInputLayoutUsername.isErrorEnabled = false
    }
}