package com.test.boobluk.utils.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.test.boobluk.R
import com.test.boobluk.screens.fragments.authentication.login.LoginFragment
import com.test.boobluk.screens.fragments.authentication.login.LoginFragmentDirections
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragment
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragmentDirections
import com.test.boobluk.screens.fragments.chat.ChatFragment
import com.test.boobluk.screens.fragments.chat.ChatFragmentDirections
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.profile.EditProfileFragmentDirections
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.search.SearchFragmentDirections
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragmentDirections

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

fun ChatFragment.goToAddNewFragment() {
    val direction = ChatFragmentDirections.actionFromChatFragmentToAddNewChatFragment()
    this.findNavController().navigate(direction)
}

fun SearchFragment.goToAddNewFragment() {
    val direction = ChatFragmentDirections.actionFromChatFragmentToAddNewChatFragment()
    this.findNavController().navigate(direction)
}

fun EditProfileFragment.goToAddNewFragment() {
    val direction = ChatFragmentDirections.actionFromChatFragmentToAddNewChatFragment()
    this.findNavController().navigate(direction)
}

fun SettingsFragment.goToAddNewFragment() {
    val direction = ChatFragmentDirections.actionFromChatFragmentToAddNewChatFragment()
    this.findNavController().navigate(direction)
}

fun FragmentManager.changeFragment(fragment: Fragment): Boolean {
    this.beginTransaction().replace(R.id.main_activity, fragment).commit()
    return true
}