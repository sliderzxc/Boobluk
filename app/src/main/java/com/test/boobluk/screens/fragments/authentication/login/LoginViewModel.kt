package com.test.boobluk.screens.fragments.authentication.login

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.firebase.authentication.login.LoginFirebaseHelper
import com.test.boobluk.utils.navigation.goToMainFragment

class LoginViewModel(
    val loginFirebaseHelper: LoginFirebaseHelper
) : ViewModel() {

    fun signInAndValidEditTexts(
        binding: FragmentLoginBinding,
        loginFragment: LoginFragment,
        firebase: Firebase
    ) {
        loginFirebaseHelper.signInAndValidEditTexts(
            binding = binding,
            loginFragment = loginFragment,
            firebase = firebase
        )
    }

    fun checkIfUserLoginAndConfirmedEmail(firebase: Firebase, loginFragment: LoginFragment) {
        loginFirebaseHelper.checkIfUserLoginAndConfirmedEmail(
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