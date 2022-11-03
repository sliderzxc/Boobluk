package com.test.boobluk.helper.navigation

import androidx.navigation.fragment.findNavController
import com.test.boobluk.screens.fragments.authentication.login.LoginFragment
import com.test.boobluk.screens.fragments.authentication.login.LoginFragmentDirections
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragment
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragmentDirections
import com.test.boobluk.screens.fragments.main.MainFragment
import com.test.boobluk.screens.fragments.main.MainFragmentDirections

fun MainFragment.goToLoginFragment() {
    val direction = MainFragmentDirections.actionFromMainFragmentToLoginFragment()
    this.findNavController().navigate(direction)
}

fun LoginFragment.goToMainFragment() {
    val direction = LoginFragmentDirections.actionFromLoginFragmentToMainFragment()
    this.findNavController().navigate(direction)
}

fun LoginFragment.goToRegisterFragment() {
    val direction = LoginFragmentDirections.actionFromLoginFragmentToRegisterFragment()
    this.findNavController().navigate(direction)
}

fun LoginFragment.goToForgotPasswordFragment() {
    val direction = LoginFragmentDirections.actionFromLoginFragmentToForgotPasswordFragment()
    this.findNavController().navigate(direction)
}

fun RegisterFragment.goToLoginFragment() {
    val direction = RegisterFragmentDirections.actionFromRegisterFragmentToLoginFragment()
    this.findNavController().navigate(direction)
}

fun RegisterFragment.goToMainFragment() {
    val direction = RegisterFragmentDirections.actionFromRegisterFragmentToMainFragment()
    this.findNavController().navigate(direction)
}