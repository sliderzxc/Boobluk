package com.test.boobluk.screens.fragments.authentication.register

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.firebase.authentication.register.RegisterFirebaseHelper
import com.test.boobluk.interfaces.authentication.register.RegisterFirebaseInterface

class RegisterViewModel(
    val registerFirebaseInterface: RegisterFirebaseInterface
) : ViewModel() {

    fun createUserAndCheckValidEditTexts(
        registerFragment: RegisterFragment,
        binding: FragmentRegisterBinding,
        firebase: Firebase
    ) {
        registerFirebaseInterface.createUserAndCheckValidEditTexts(
            registerFragment = registerFragment,
            binding = binding,
            firebase = firebase
        )
    }

    fun checkIfUserRegisteredAndConfirmedEmail(
        firebase: Firebase,
        registerFragment:
        RegisterFragment) {
        registerFirebaseInterface.checkIfUserRegisteredAndConfirmedEmail(
            auth = firebase.auth,
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
            binding.textInputLayoutConfirmPassword.error = null
            binding.textInputLayoutConfirmPassword.isErrorEnabled = false
        }
        binding.etUsername.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutUsername.error = null
            binding.textInputLayoutUsername.isErrorEnabled = false
        }
    }

}