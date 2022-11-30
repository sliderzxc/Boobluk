package com.test.boobluk.interfaces.authentication.password

import com.google.firebase.auth.FirebaseAuth
import com.test.boobluk.databinding.FragmentForgotPasswordBinding
import com.test.boobluk.screens.fragments.authentication.password.ForgotPasswordFragment

interface ForgotPasswordFirebaseInterface {

    fun sendPasswordResetAndValidEditTexts(
        binding: FragmentForgotPasswordBinding,
        forgotPasswordFragment: ForgotPasswordFragment,
        auth: FirebaseAuth
    )

}