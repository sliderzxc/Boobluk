package com.test.boobluk.screens.fragments.authentication.login

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.firebase.authentication.login.LoginFirebaseHelper
import com.test.boobluk.interfaces.authentication.login.LoginFirebaseInterface

class LoginViewModel(
    val loginFirebaseInterface: LoginFirebaseInterface
) : ViewModel() {

    fun signInAndValidEditTexts(
        binding: FragmentLoginBinding,
        loginFragment: LoginFragment,
        firebase: Firebase
    ) {
        loginFirebaseInterface.signInAndValidEditTexts(
            binding = binding,
            loginFragment = loginFragment,
            firebase = firebase
        )
    }

    fun checkIfUserLoginAndConfirmedEmail(firebase: Firebase, loginFragment: LoginFragment) {
        loginFirebaseInterface.checkIfUserLoginAndConfirmedEmail(
            firebase = firebase,
            loginFragment = loginFragment
        )
    }

    fun doOnTextsChanged(binding: FragmentLoginBinding) {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutEmail.error = null
            binding.textInputLayoutEmail.isErrorEnabled = false
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutPassword.error = null
            binding.textInputLayoutPassword.isErrorEnabled = false
        }
        binding.etUsername.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutUsername.error = null
            binding.textInputLayoutUsername.isErrorEnabled = false
        }
    }

}