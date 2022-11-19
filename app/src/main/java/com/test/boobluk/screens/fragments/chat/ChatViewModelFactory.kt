package com.test.boobluk.screens.fragments.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.chat.ChatFirebaseHelper
import javax.inject.Inject

class ChatViewModelFactory @Inject constructor(
    val chatFirebaseHelper: ChatFirebaseHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(
            chatFirebaseHelper = chatFirebaseHelper
        ) as T
    }

}