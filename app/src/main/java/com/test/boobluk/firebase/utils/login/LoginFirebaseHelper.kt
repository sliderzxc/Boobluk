package com.test.boobluk.firebase.utils.login

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.firebase.database.loginAndUpdateUserInDatabase
import com.test.boobluk.screens.fragments.authentication.login.LoginFragment
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.navigation.goToMainFragment
import com.test.boobluk.utils.toast.showDarkMotionInfoColorToast

class LoginFirebaseHelper {

    fun signInAndValidEditTexts(binding: FragmentLoginBinding, loginFragment: LoginFragment, firebase: Firebase) {
        val username = binding.etUsername.text?.trim()
        val email = binding.etEmail.text?.trim()
        val password = binding.etPassword.text?.trim()

        if (username.isNullOrEmpty()) {
            clearTextInputLayoutPasswordError(binding = binding)
            clearTextInputLayoutEmailError(binding = binding)
            binding.textInputLayoutUsername.error = loginFragment.getString(R.string.username_is_empty)
            return
        }

        if (username.toString().length < 3) {
            clearTextInputLayoutPasswordError(binding = binding)
            clearTextInputLayoutEmailError(binding = binding)
            binding.textInputLayoutUsername.error = loginFragment.getString(R.string.username_is_too_short)
            return
        }

        if (email.isNullOrEmpty()) {
            clearTextInputLayoutPasswordError(binding = binding)
            clearTextInputLayoutUsernameError(binding = binding)
            binding.textInputLayoutEmail.error = loginFragment.getString(R.string.email_is_empty)
            return
        }

        if (password.isNullOrEmpty()) {
            clearTextInputLayoutEmailError(binding = binding)
            clearTextInputLayoutUsernameError(binding = binding)
            binding.textInputLayoutPassword.error = loginFragment.getString(R.string.password_is_empty)
            return
        }

        if (password.length < 6) {
            clearTextInputLayoutEmailError(binding = binding)
            clearTextInputLayoutUsernameError(binding = binding)
            binding.textInputLayoutPassword.error = loginFragment.getString(R.string.password_is_too_short)
            return
        }

        firebase.auth.signInWithEmailAndPassword(email.toString(), password.toString()).addOnSuccessListener {
            if (firebase.auth.currentUser?.isEmailVerified == true) {
                loginAndUpdateUserInDatabase(
                    firebase = firebase,
                    binding = binding,
                    loginFragment = loginFragment,
                    loginFirebaseHelper = this
                )
            }
            if (firebase.auth.currentUser?.isEmailVerified == false){
                showDarkMotionInfoColorToast(fragment = loginFragment, loginFragment.getString(R.string.check_email_to_confirm_account))
            }
        }.addOnFailureListener {
            val exception = it.message.toString()

            if (exception == Constants.PASSWORD_IS_INCORRECT_INFO) {
                clearTextInputLayoutEmailError(binding = binding)
                clearTextInputLayoutUsernameError(binding = binding)
                binding.textInputLayoutPassword.error = Constants.PASSWORD_IS_INCORRECT
                return@addOnFailureListener
            }

            if (exception == Constants.EMAIL_ADDRESS_IS_INCORRECT) {
                clearTextInputLayoutPasswordError(binding = binding)
                clearTextInputLayoutUsernameError(binding = binding)
                binding.textInputLayoutEmail.error = Constants.EMAIL_ADDRESS_IS_INCORRECT
                return@addOnFailureListener
            }

            if (exception == Constants.EMAIL_WAS_NOT_FOUND_INFO) {
                clearTextInputLayoutPasswordError(binding = binding)
                clearTextInputLayoutUsernameError(binding = binding)
                binding.textInputLayoutEmail.error = Constants.EMAIL_WAS_NOT_FOUND
                return@addOnFailureListener
            }
        }
    }

    fun checkIfUserLoginAndConfirmedEmail(firebase: Firebase, loginFragment: LoginFragment) {
        if (firebase.auth.currentUser != null && firebase.auth.currentUser?.isEmailVerified == true) {
            loginFragment.goToMainFragment()
        }
    }

    fun clearTextInputLayoutPasswordError(binding: FragmentLoginBinding) {
        binding.textInputLayoutPassword.error = null
        binding.textInputLayoutPassword.isErrorEnabled = false
    }

    fun clearTextInputLayoutEmailError(binding: FragmentLoginBinding) {
        binding.textInputLayoutEmail.error = null
        binding.textInputLayoutEmail.isErrorEnabled = false
    }

    private fun clearTextInputLayoutUsernameError(binding: FragmentLoginBinding) {
        binding.textInputLayoutUsername.error = null
        binding.textInputLayoutUsername.isErrorEnabled = false
    }

}