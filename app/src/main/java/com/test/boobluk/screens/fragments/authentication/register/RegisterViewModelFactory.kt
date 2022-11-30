package com.test.boobluk.screens.fragments.authentication.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.authentication.register.RegisterFirebaseHelper
import com.test.boobluk.interfaces.authentication.register.RegisterFirebaseInterface
import javax.inject.Inject

class RegisterViewModelFactory @Inject constructor(
    val registerFirebaseInterface: RegisterFirebaseInterface
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(
            registerFirebaseInterface = registerFirebaseInterface
        ) as T
    }
}