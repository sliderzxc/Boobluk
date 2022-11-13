package com.test.boobluk.screens.fragments.authentication.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.utils.register.RegisterFirebaseHelper
import javax.inject.Inject

class RegisterViewModelFactory @Inject constructor(
    val registerFirebaseHelper: RegisterFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(
            registerFirebaseHelper = registerFirebaseHelper
        ) as T
    }
}