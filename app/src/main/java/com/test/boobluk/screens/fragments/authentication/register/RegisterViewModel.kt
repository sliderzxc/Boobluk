package com.test.boobluk.screens.fragments.authentication.register

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.firebase.register.RegisterFirebaseHelper

class RegisterViewModel(
    val registerFirebaseHelper: RegisterFirebaseHelper
) : ViewModel() {

    fun createUserAndCheckValidEditTexts(
        registerFragment: RegisterFragment,
        binding: FragmentRegisterBinding,
        auth: FirebaseAuth
    ) {
        registerFirebaseHelper.createUserAndCheckValidEditTexts(
            registerFragment = registerFragment,
            binding = binding,
            auth = auth
        )
    }

    fun checkIfUserRegisteredAndConfirmedEmail(
        auth: FirebaseAuth,
        registerFragment:
        RegisterFragment) {
        registerFirebaseHelper.checkIfUserRegisteredAndConfirmedEmail(
            auth = auth,
            registerFragment = registerFragment
        )
    }

    fun doOnTextsChanges(binding: FragmentRegisterBinding) {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutPassword.error = null
            binding.textInputLayoutPassword.isErrorEnabled = false
        }
        binding.etConfirmPassword.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutConfirmPassword.isErrorEnabled = false
        }
    }

}