package com.test.boobluk.screens.fragments.authentication.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.authentication.password.ForgotPasswordFirebaseHelper
import javax.inject.Inject

class ForgotPasswordViewModelFactory @Inject constructor(
    val forgotPasswordFirebaseHelper: ForgotPasswordFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForgotPasswordViewModel(
            forgotPasswordFirebaseHelper = forgotPasswordFirebaseHelper
        ) as T
    }
}