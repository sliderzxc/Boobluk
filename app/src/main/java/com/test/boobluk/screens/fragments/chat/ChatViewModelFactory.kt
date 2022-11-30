package com.test.boobluk.screens.fragments.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.chat.ChatFirebaseHelper
import com.test.boobluk.interfaces.chat.ChatFirebaseInterface
import javax.inject.Inject

class ChatViewModelFactory @Inject constructor(
    val chatFirebaseInterface: ChatFirebaseInterface
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(
            chatFirebaseInterface = chatFirebaseInterface
        ) as T
    }

}