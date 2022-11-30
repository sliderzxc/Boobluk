package com.test.boobluk.screens.fragments.authentication.password

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.test.boobluk.databinding.FragmentForgotPasswordBinding
import com.test.boobluk.firebase.authentication.password.ForgotPasswordFirebaseHelper
import com.test.boobluk.interfaces.authentication.password.ForgotPasswordFirebaseInterface

class ForgotPasswordViewModel(
    val forgotPasswordFirebaseInterface: ForgotPasswordFirebaseInterface
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
        forgotPasswordFirebaseInterface.sendPasswordResetAndValidEditTexts(
            binding = binding,
            forgotPasswordFragment = forgotPasswordFragment,
            auth = auth
        )
    }

}