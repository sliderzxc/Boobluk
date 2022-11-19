package com.test.boobluk.screens.fragments.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.chats.ListOfChatsFirebaseHelper
import javax.inject.Inject

class ListOfChatsViewModelFactory @Inject constructor(
    val listOfChatsFirebaseHelper: ListOfChatsFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListOfChatsViewModel(
            listOfChatsFirebaseHelper = listOfChatsFirebaseHelper
        ) as T
    }
}