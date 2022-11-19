package com.test.boobluk.screens.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.profile.EditProfileFirebaseHelper
import com.test.boobluk.utils.image.ImageHelper
import kotlinx.coroutines.delay
import javax.inject.Inject

class EditProfileViewModelFactory @Inject constructor(
    val editProfileFirebaseHelper: EditProfileFirebaseHelper,
    val imageHelper: ImageHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditProfileViewModel(
            editProfileFirebaseHelper = editProfileFirebaseHelper,
            imageHelper = imageHelper
        ) as T
    }
}