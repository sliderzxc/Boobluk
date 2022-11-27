package com.test.boobluk.firebase.authentication.register

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.test.boobluk.R
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragment
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.constants.Constants.EMAIL_ADDRESS_IS_BUSY
import com.test.boobluk.utils.constants.Constants.EMAIL_ADDRESS_IS_INCORRECT
import com.test.boobluk.utils.navigation.goToLoginFragment
import com.test.boobluk.utils.toast.showDarkMotionInfoColorToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFirebaseHelper {

    fun checkIfUserRegisteredAndConfirmedEmail(auth: FirebaseAuth, registerFragment: RegisterFragment) {
        if (auth.currentUser != null && auth.currentUser?.isEmailVerified == true) {
            registerFragment.goToLoginFragment()
        }
    }

    fun createUserAndCheckValidEditTexts(
        registerFragment: RegisterFragment,
        binding: FragmentRegisterBinding,
        firebase: Firebase
    ) {
        val username = binding.etUsername.text?.trim()
        val email = binding.etEmail.text?.trim()
        val password = binding.etPassword.text?.trim()
        val confirmPassword = binding.etConfirmPassword.text?.trim()

        if (username.isNullOrEmpty()) {
            clearTextInputLayoutEmailError(binding = binding)
            clearTextInputLayoutPasswordError(binding = binding)
            clearTextInputLayoutConfirmPasswordError(binding = binding)
            binding.textInputLayoutUsername.error = registerFragment.getString(R.string.username_is_empty)
            return
        }

        if (username.toString().length < 3) {
            clearTextInputLayoutEmailError(binding = binding)
            clearTextInputLayoutPasswordError(binding = binding)
            clearTextInputLayoutConfirmPasswordError(binding = binding)
            binding.textInputLayoutUsername.error = registerFragment.getString(R.string.username_is_too_short)
            return
        }

        if (email.isNullOrEmpty()) {
            clearTextInputLayoutConfirmPasswordError(binding = binding)
            clearTextInputLayoutPasswordError(binding = binding)
            clearTextInputLayoutUsernameError(binding = binding)
            binding.textInputLayoutEmail.error = registerFragment.getString(R.string.email_is_empty)
            return
        }

        if (password.isNullOrEmpty()) {
            clearTextInputLayoutEmailError(binding = binding)
            clearTextInputLayoutConfirmPasswordError(binding = binding)
            clearTextInputLayoutUsernameError(binding = binding)
            binding.textInputLayoutPassword.error = registerFragment.getString(R.string.password_is_empty)
            return
        }

        if (password.length < 6) {
            clearTextInputLayoutEmailError(binding = binding)
            clearTextInputLayoutConfirmPasswordError(binding = binding)
            clearTextInputLayoutUsernameError(binding = binding)
            binding.textInputLayoutPassword.error = registerFragment.getString(R.string.password_is_too_short)
            return
        }

        if (confirmPassword.isNullOrEmpty()) {
            clearTextInputLayoutEmailError(binding = binding)
            clearTextInputLayoutPasswordError(binding = binding)
            clearTextInputLayoutUsernameError(binding = binding)
            binding.textInputLayoutConfirmPassword.error = registerFragment.getString(R.string.password_is_empty)
            return
        }

        if (password.toString() != confirmPassword.toString()) {
            clearTextInputLayoutEmailError(binding = binding)
            clearTextInputLayoutPasswordError(binding = binding)
            clearTextInputLayoutUsernameError(binding = binding)
            binding.textInputLayoutConfirmPassword.error = registerFragment.getString(R.string.password_do_not_match)
            return
        }

        firebase.auth.createUserWithEmailAndPassword(email.toString(), password.toString()).addOnSuccessListener {
            registerAndAddUserToDatabase(firebase, binding)
            firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                showDarkMotionInfoColorToast(
                    fragment = registerFragment,
                    text = registerFragment.getString(R.string.check_email_to_confirm_account)
                )
            }
        }.addOnFailureListener {
            val exception = it.message.toString()

            if (exception == EMAIL_ADDRESS_IS_BUSY) {
                clearTextInputLayoutPasswordError(binding = binding)
                clearTextInputLayoutConfirmPasswordError(binding = binding)
                binding.textInputLayoutEmail.error = EMAIL_ADDRESS_IS_BUSY
                return@addOnFailureListener
            }

            if (exception == EMAIL_ADDRESS_IS_INCORRECT) {
                clearTextInputLayoutPasswordError(binding = binding)
                clearTextInputLayoutConfirmPasswordError(binding = binding)
                return@addOnFailureListener
            }
        }
    }

    private fun clearTextInputLayoutPasswordError(binding: FragmentRegisterBinding) {
        binding.textInputLayoutPassword.error = null
        binding.textInputLayoutPassword.isErrorEnabled = false
    }

    private fun clearTextInputLayoutConfirmPasswordError(binding: FragmentRegisterBinding) {
        binding.textInputLayoutConfirmPassword.error = null
        binding.textInputLayoutConfirmPassword.isErrorEnabled = false
    }

    private fun clearTextInputLayoutEmailError(binding: FragmentRegisterBinding) {
        binding.textInputLayoutEmail.error = null
        binding.textInputLayoutEmail.isErrorEnabled = false
    }

    private fun clearTextInputLayoutUsernameError(binding: FragmentRegisterBinding) {
        binding.textInputLayoutUsername.error = null
        binding.textInputLayoutUsername.isErrorEnabled = false
    }

    private fun registerAndAddUserToDatabase(
        firebase: Firebase,
        binding: FragmentRegisterBinding
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val uid = firebase.auth.currentUser?.uid.toString()
            val email = binding.etEmail.text.toString()
            val username = binding.etUsername.text.toString()

            firebase.storage.getReferenceFromUrl(Constants.REFERENCE_NEW_USER_AVATAR).downloadUrl.addOnSuccessListener { uri ->
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
                firebase.firestore.collection(Constants.REFERENCE_USER_INFO).document(uid).set(userInfo)
            }
        }
    }

}