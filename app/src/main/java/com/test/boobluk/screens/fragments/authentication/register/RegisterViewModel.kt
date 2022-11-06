package com.test.boobluk.screens.fragments.authentication.register

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.firebase.utils.register.RegisterFirebaseHelper

class RegisterViewModel(
    val registerFirebaseHelper: RegisterFirebaseHelper
) : ViewModel() {

    fun createUserAndCheckValidEditTexts(
        registerFragment: RegisterFragment,
        binding: FragmentRegisterBinding,
        firebase: Firebase
    ) {
        registerFirebaseHelper.createUserAndCheckValidEditTexts(
            registerFragment = registerFragment,
            binding = binding,
            firebase = firebase
        )
    }

    fun checkIfUserRegisteredAndConfirmedEmail(
        firebase: Firebase,
        registerFragment:
        RegisterFragment) {
        registerFirebaseHelper.checkIfUserRegisteredAndConfirmedEmail(
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