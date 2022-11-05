package com.test.boobluk.screens.fragments.authentication.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.register.RegisterFirebaseHelper
import javax.inject.Inject

class RegisterViewModelFactory @Inject constructor(
    val firebaseHelper: RegisterFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(
            registerFirebaseHelper = firebaseHelper
        ) as T
    }
}