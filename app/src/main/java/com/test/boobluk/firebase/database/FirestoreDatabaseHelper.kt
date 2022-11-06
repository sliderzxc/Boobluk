package com.test.boobluk.firebase.database

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test.boobluk.data.entities.User
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.databinding.FragmentRegisterBinding

fun registerAndAddUserToDatabase(
    firebase: Firebase,
    binding: FragmentRegisterBinding
) {
    val email = binding.etEmail.text.toString()
    val uid = firebase.auth.currentUser?.uid.toString()
    val username = binding.etUsername.text.toString()

    val user = User(
        email = email,
        isEmailConfirmed = false,
        uid = uid,
        username = username
    )
    firebase.firestore.collection("users").document(uid).set(user)
}

fun loginAndUpdateUserInDatabase(
    firebase: Firebase,
    binding: FragmentLoginBinding
) {
    val uid = firebase.auth.currentUser?.uid.toString()
    val email = binding.etEmail.text.toString()
    val username = binding.etUsername.text.toString()

    val user = User(
        email = email,
        isEmailConfirmed = true,
        uid = uid,
        username = username
    )
    firebase.firestore.collection("users").document(uid).set(user)
}