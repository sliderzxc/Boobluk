package com.test.boobluk.screens.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.profile.EditProfileFirebaseHelper
import com.test.boobluk.interfaces.profile.EditProfileFirebaseInterface
import com.test.boobluk.utils.image.ImageHelper
import javax.inject.Inject

class EditProfileViewModelFactory @Inject constructor(
    val editProfileFirebaseInterface: EditProfileFirebaseInterface,
    val imageHelper: ImageHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditProfileViewModel(
            editProfileFirebaseInterface = editProfileFirebaseInterface,
            imageHelper = imageHelper
        ) as T
    }
}