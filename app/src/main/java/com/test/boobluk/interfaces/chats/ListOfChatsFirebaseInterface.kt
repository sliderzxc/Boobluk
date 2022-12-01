package com.test.boobluk.interfaces.chats

import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.ChatAdapter
import com.test.boobluk.databinding.FragmentListOfChatsBinding

interface ListOfChatsFirebaseInterface {

    fun getAllChats(
        binding: FragmentListOfChatsBinding,
        firebase: Firebase,
        chatAdapter: ChatAdapter
    )

    fun deleteChat(
        firebase: Firebase,
        interlocutorUid: String
    )

}