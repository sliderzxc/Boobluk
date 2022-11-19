package com.test.boobluk.screens.fragments.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.add.AddNewChatFirebaseHelper
import javax.inject.Inject

class AddNewChatViewModelFactory @Inject constructor(
    val addNewChatFirebaseHelper: AddNewChatFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddNewChatViewModel(
            addNewChatFirebaseHelper = addNewChatFirebaseHelper
        ) as T
    }
}