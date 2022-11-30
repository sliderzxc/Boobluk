package com.test.boobluk.screens.fragments.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.interfaces.add.AddNewChatFirebaseInterface
import javax.inject.Inject

class AddNewChatViewModelFactory @Inject constructor(
    val addNewChatFirebaseInterface: AddNewChatFirebaseInterface
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddNewChatViewModel(
            addNewChatFirebaseInterface = addNewChatFirebaseInterface
        ) as T
    }
}