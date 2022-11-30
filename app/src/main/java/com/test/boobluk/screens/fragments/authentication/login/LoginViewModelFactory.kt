package com.test.boobluk.screens.fragments.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.authentication.login.LoginFirebaseHelper
import com.test.boobluk.interfaces.authentication.login.LoginFirebaseInterface
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    val loginFirebaseInterface: LoginFirebaseInterface
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(
            loginFirebaseInterface = loginFirebaseInterface
        ) as T
    }
}