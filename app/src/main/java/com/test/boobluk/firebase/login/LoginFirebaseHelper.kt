package com.test.boobluk.firebase.login

import com.google.firebase.auth.FirebaseAuth
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.screens.fragments.authentication.login.LoginFragment
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.navigation.goToMainFragment
import com.test.boobluk.utils.toast.showDarkMotionColorToast

class LoginFirebaseHelper {

    fun signInAndValidEditTexts(binding: FragmentLoginBinding, loginFragment: LoginFragment, auth: FirebaseAuth) {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (binding.etEmail.text.isNullOrEmpty()) {
            binding.textInputLayoutPassword.error = null
            binding.textInputLayoutPassword.isErrorEnabled = false
            binding.textInputLayoutEmail.error = loginFragment.getString(R.string.email_is_empty)
            return
        }

        if (binding.etPassword.text.isNullOrEmpty()) {
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
            binding.textInputLayoutPassword.error = loginFragment.getString(R.string.password_is_empty)
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            if (auth.currentUser?.isEmailVerified == true) {
                loginFragment.goToMainFragment()
            }
            if (auth.currentUser?.isEmailVerified == false){
                showDarkMotionColorToast(fragment = loginFragment, loginFragment.getString(R.string.check_email_to_confirm_account))
            }
        }.addOnFailureListener {
            val exception = it.message.toString()

            if (exception == Constants.PASSWORD_IS_INCORRECT_INFO) {
                binding.textInputLayoutPassword.error = Constants.PASSWORD_IS_INCORRECT
                binding.textInputLayoutEmail.error = null
                binding.textInputLayoutEmail.isErrorEnabled = false
                return@addOnFailureListener
            }

            if (exception == Constants.EMAIL_ADDRESS_IS_INCORRECT) {
                binding.textInputLayoutEmail.error = Constants.EMAIL_ADDRESS_IS_INCORRECT
                binding.textInputLayoutPassword.error = null
                binding.textInputLayoutPassword.isErrorEnabled = false
                return@addOnFailureListener
            }

            if (exception == Constants.EMAIL_WAS_NOT_FOUND_INFO) {
                binding.textInputLayoutEmail.error = Constants.EMAIL_WAS_NOT_FOUND
                binding.textInputLayoutPassword.error = null
                binding.textInputLayoutPassword.isErrorEnabled = false
                return@addOnFailureListener
            }
        }
    }
}