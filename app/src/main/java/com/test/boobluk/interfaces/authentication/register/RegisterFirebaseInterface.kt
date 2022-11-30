package com.test.boobluk.interfaces.authentication.register

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragment

interface RegisterFirebaseInterface {

    fun checkIfUserRegisteredAndConfirmedEmail(auth: FirebaseAuth, registerFragment: RegisterFragment)

    fun createUserAndCheckValidEditTexts(
        registerFragment: RegisterFragment,
        binding: FragmentRegisterBinding,
        firebase: Firebase
    )

    fun registerAndAddUserToDatabase(
        firebase: Firebase,
        binding: FragmentRegisterBinding
    )

}