package com.test.boobluk.firebase.utils.password

import com.google.firebase.auth.FirebaseAuth
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentForgotPasswordBinding
import com.test.boobluk.screens.fragments.authentication.password.ForgotPasswordFragment
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.toast.showDarkMotionInfoColorToast

class ForgotPasswordFirebaseHelper {

    fun sendPasswordResetAndValidEditTexts(
        binding: FragmentForgotPasswordBinding,
        forgotPasswordFragment: ForgotPasswordFragment,
        auth: FirebaseAuth
    ) {
        val email = binding.etEmail.text

        if (email.isNullOrEmpty()) {
            binding.textInputLayoutEmail.error = forgotPasswordFragment.getString(R.string.email_is_empty)
            return
        }
        auth.sendPasswordResetEmail(email.toString()).addOnSuccessListener {
            showDarkMotionInfoColorToast(forgotPasswordFragment, forgotPasswordFragment.getString(R.string.check_email_to_get_instructions))
        }.addOnFailureListener {
            val exception = it.message.toString()

            if (exception == Constants.EMAIL_ADDRESS_IS_INCORRECT) {
                binding.textInputLayoutEmail.error = Constants.EMAIL_ADDRESS_IS_INCORRECT
                return@addOnFailureListener
            }

            if (exception == Constants.EMAIL_WAS_NOT_FOUND_INFO) {
                binding.textInputLayoutEmail.error = Constants.EMAIL_WAS_NOT_FOUND
                return@addOnFailureListener
            }
        }
    }
}