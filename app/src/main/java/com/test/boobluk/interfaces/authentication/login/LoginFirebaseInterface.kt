package com.test.boobluk.interfaces.authentication.login

import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.firebase.authentication.login.LoginFirebaseHelper
import com.test.boobluk.screens.fragments.authentication.login.LoginFragment

interface LoginFirebaseInterface {

    fun signInAndValidEditTexts(
        binding: FragmentLoginBinding,
        loginFragment: LoginFragment,
        firebase: Firebase
    )

    fun checkIfUserLoginAndConfirmedEmail(
        firebase: Firebase,
        loginFragment: LoginFragment
    )

    fun loginAndUpdateUserInDatabase(
        firebase: Firebase,
        binding: FragmentLoginBinding,
        loginFragment: LoginFragment,
        loginFirebaseHelper: LoginFirebaseHelper
    )

}