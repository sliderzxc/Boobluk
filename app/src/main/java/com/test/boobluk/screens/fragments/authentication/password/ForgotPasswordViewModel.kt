package com.test.boobluk.screens.fragments.authentication.password

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.test.boobluk.databinding.FragmentForgotPasswordBinding
import com.test.boobluk.firebase.utils.password.ForgotPasswordFirebaseHelper

class ForgotPasswordViewModel(
    val forgotPasswordFirebaseHelper: ForgotPasswordFirebaseHelper
) : ViewModel() {

    fun doOnTextChanged(binding: FragmentForgotPasswordBinding) {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
        }
    }

    fun sendPasswordResetAndValidEditTexts(
        binding: FragmentForgotPasswordBinding,
        forgotPasswordFragment: ForgotPasswordFragment,
        auth: FirebaseAuth
    ) {
        forgotPasswordFirebaseHelper.sendPasswordResetAndValidEditTexts(
            binding = binding,
            forgotPasswordFragment = forgotPasswordFragment,
            auth = auth
        )
    }

}