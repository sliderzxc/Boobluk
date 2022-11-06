package com.test.boobluk.screens.fragments.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.utils.login.LoginFirebaseHelper
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    val loginFirebaseHelper: LoginFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(
            loginFirebaseHelper = loginFirebaseHelper
        ) as T
    }
}