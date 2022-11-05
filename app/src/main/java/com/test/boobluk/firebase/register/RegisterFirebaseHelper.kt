package com.test.boobluk.firebase.register

import com.google.firebase.auth.FirebaseAuth
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragment
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.constants.Constants.EMAIL_ADDRESS_IS_BUSY
import com.test.boobluk.utils.constants.Constants.EMAIL_ADDRESS_IS_INCORRECT
import com.test.boobluk.utils.navigation.goToLoginFragment
import com.test.boobluk.utils.toast.showDarkMotionColorToast

class RegisterFirebaseHelper {
    fun checkIfUserRegisteredAndConfirmedEmail(auth: FirebaseAuth, registerFragment: RegisterFragment) {
        if (auth.currentUser != null && auth.currentUser?.isEmailVerified == true) {
            registerFragment.goToLoginFragment()
        }
    }

    fun createUserAndCheckValidEditTexts(
        registerFragment: RegisterFragment,
        binding: FragmentRegisterBinding,
        auth: FirebaseAuth
    ) {
        val email = binding.etEmail.text
        val password = binding.etPassword.text
        val confirmPassword = binding.etConfirmPassword.text

        if (email.isNullOrEmpty()) {
            binding.textInputLayoutConfirmPassword.error = null
            binding.textInputLayoutConfirmPassword.isErrorEnabled = false
            binding.textInputLayoutPassword.error = null
            binding.textInputLayoutPassword.isErrorEnabled = false
            binding.textInputLayoutEmail.error = registerFragment.getString(R.string.email_is_empty)
            return
        }

        if (password.isNullOrEmpty()) {
            binding.textInputLayoutConfirmPassword.error = null
            binding.textInputLayoutConfirmPassword.isErrorEnabled = false
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
            binding.textInputLayoutPassword.error = registerFragment.getString(R.string.password_is_empty)
            return
        }

        if (password.length < 6) {
            binding.textInputLayoutConfirmPassword.error = null
            binding.textInputLayoutConfirmPassword.isErrorEnabled = false
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
            binding.textInputLayoutPassword.error = registerFragment.getString(R.string.password_is_too_short)
            return
        }

        if (confirmPassword.isNullOrEmpty()) {
            binding.textInputLayoutPassword.error = null
            binding.textInputLayoutPassword.isErrorEnabled = false
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
            binding.textInputLayoutConfirmPassword.error = registerFragment.getString(R.string.password_is_empty)
            return
        }

        if (password.toString() != confirmPassword.toString()) {
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
            binding.textInputLayoutPassword.error = null
            binding.textInputLayoutPassword.isErrorEnabled = false
            binding.textInputLayoutConfirmPassword.error = registerFragment.getString(R.string.password_do_not_match)
            return
        }

        auth.createUserWithEmailAndPassword(email.toString(), password.toString()).addOnSuccessListener {
            auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                showDarkMotionColorToast(
                    fragment = registerFragment,
                    text = registerFragment.getString(R.string.check_email_to_confirm_account)
                )
            }
        }.addOnFailureListener {
            val exception = it.message.toString()

            if (exception == EMAIL_ADDRESS_IS_BUSY) {
                binding.textInputLayoutPassword.error = null
                binding.textInputLayoutPassword.isErrorEnabled = false
                binding.textInputLayoutConfirmPassword.error = null
                binding.textInputLayoutConfirmPassword.isErrorEnabled = false
                binding.textInputLayoutEmail.error = EMAIL_ADDRESS_IS_BUSY
                return@addOnFailureListener
            }

            if (exception == EMAIL_ADDRESS_IS_INCORRECT) {
                binding.textInputLayoutPassword.error = null
                binding.textInputLayoutPassword.isErrorEnabled = false
                binding.textInputLayoutConfirmPassword.error = null
                binding.textInputLayoutConfirmPassword.isErrorEnabled = false
                binding.textInputLayoutEmail.error = EMAIL_ADDRESS_IS_INCORRECT
                return@addOnFailureListener
            }
        }
    }
}