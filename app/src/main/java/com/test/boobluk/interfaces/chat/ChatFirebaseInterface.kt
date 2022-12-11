package com.test.boobluk.interfaces.chat

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.MessageAdapter
import com.test.boobluk.data.entities.messages.Message
import com.test.boobluk.databinding.FragmentChatBinding
import com.test.boobluk.network.viewmodel.NotificationViewModel
import com.test.boobluk.screens.fragments.chat.ChatViewModel

interface ChatFirebaseInterface {

    fun changeInChatWithInFirebase(
        firebase: Firebase,
        interlocutorUid: String
    )

    fun clearInChatWithInFirebase(firebase: Firebase)

    fun checkIfExistsAndClearNotificationsInThisChat(
        interlocutorUid: String,
        firebase: Firebase,
        context: Context
    )

    fun getUserDataAndUpdateDesign(
        interlocutorUid: String,
        firebase: Firebase,
        binding: FragmentChatBinding
    )

    fun sendMessage(
        firebase: Firebase,
        binding: FragmentChatBinding,
        chatViewModel: ChatViewModel,
        messageAdapter: MessageAdapter,
        notificationViewModel: NotificationViewModel
    )

    fun getMessagesFromFirebaseAndAddToRecyclerView(
        firebase: Firebase,
        chatViewModel: ChatViewModel,
        messageAdapter: MessageAdapter,
        binding: FragmentChatBinding
    )

    fun getDataChangesAndUpdateMessageAdapter(
        firebase: Firebase,
        messageAdapter: MessageAdapter,
        chatViewModel: ChatViewModel
    )

    fun deleteMessage(
        firebase: Firebase,
        chatViewModel: ChatViewModel,
        message: Message
    )

    fun editMessage(
        firebase: Firebase,
        chatViewModel: ChatViewModel,
        message: Message,
        chatBinding: FragmentChatBinding,
    )

    fun updateLastMessage(
        firebase: Firebase,
        uid: String,
        interlocutorUid: String
    )

}