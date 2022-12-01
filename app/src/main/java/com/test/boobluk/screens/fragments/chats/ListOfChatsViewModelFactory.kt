package com.test.boobluk.screens.fragments.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.chats.ListOfChatsFirebaseHelper
import com.test.boobluk.interfaces.chats.ListOfChatsFirebaseInterface
import javax.inject.Inject

class ListOfChatsViewModelFactory @Inject constructor(
    val listOfChatsFirebaseInterface: ListOfChatsFirebaseInterface
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListOfChatsViewModel(
            listOfChatsFirebaseInterface = listOfChatsFirebaseInterface
        ) as T
    }
}