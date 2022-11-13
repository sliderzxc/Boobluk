package com.test.boobluk.screens.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.utils.profile.EditProfileFirebaseHelper
import kotlinx.coroutines.delay
import javax.inject.Inject

class EditProfileViewModelFactory @Inject constructor(
    val editProfileFirebaseHelper: EditProfileFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditProfileViewModel(
            editProfileFirebaseHelper = editProfileFirebaseHelper
        ) as T
    }
}