package com.test.boobluk.screens.fragments.authentication.login

import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.firebase.login.LoginFirebaseHelper
import com.test.boobluk.utils.navigation.goToMainFragment
import kotlin.math.log

class LoginViewModel(
    val loginFirebaseHelper: LoginFirebaseHelper
) : ViewModel() {

    fun signInAndValidEditTexts(
        binding: FragmentLoginBinding,
        loginFragment: LoginFragment,
        auth: FirebaseAuth
    ) {
        loginFirebaseHelper.signInAndValidEditTexts(
            binding = binding,
            loginFragment = loginFragment,
            auth = auth
        )
    }

    fun checkIfUserLoginAndConfirmedEmail(auth: FirebaseAuth, loginFragment: LoginFragment) {
        if (auth.currentUser != null && auth.currentUser?.isEmailVerified == true) {
            loginFragment.goToMainFragment()
        }
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
    }

}