package com.test.boobluk.firebase.chat

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.MessageAdapter
import com.test.boobluk.data.entities.MessageForClient
import com.test.boobluk.data.entities.MessageForServer
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentChatBinding
import com.test.boobluk.screens.fragments.chat.ChatViewModel
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.constants.Constants.REFERENCES_INIT_REALTIME_DATABASE
import com.test.boobluk.utils.constants.Constants.REFERENCE_CHATS
import com.test.boobluk.utils.constants.Constants.REFERENCE_RECEIVED_MESSAGES
import com.test.boobluk.utils.constants.Constants.REFERENCE_SENT_MESSAGES
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_CHATS
import java.text.SimpleDateFormat
import java.util.*

class ChatFirebaseHelper {

    fun getUserDataAndUpdateDesign(
        interlocutorUid: String,
        firebase: Firebase,
        binding: FragmentChatBinding
    ) {
        firebase.firestore.collection(Constants.REFERENCE_USER_INFO).document(interlocutorUid).get()
            .addOnSuccessListener {
                val user = it.toObject<UserInfo>()
                binding.tvUsername.text = user?.username
                Glide.with(binding.root).load(user?.avatar).into(binding.ivUserAvatar)
            }
    }

    @SuppressLint("SimpleDateFormat")
    fun sendMessage(
        firebase: Firebase,
        binding: FragmentChatBinding,
        chatViewModel: ChatViewModel,
        messageAdapter: MessageAdapter
    ) {
        binding.btnSendMessage.setOnClickListener {
            val uid = firebase.auth.currentUser?.uid.toString()
            val interlocutorUid = chatViewModel.userUid.value.toString()

            val textMessage = binding.etMessage.text.toString()
            if (textMessage.isEmpty()) {
                return@setOnClickListener
            }

            val currentTimeMillis = System.currentTimeMillis().toString()
            val dateFormat = SimpleDateFormat("HH:mm:ss d.MM.yyyy")
            val date = dateFormat.format(Date())

            val sharedDataFormat = SimpleDateFormat("HH:mm:ss d.MM.yyyy")
            sharedDataFormat.timeZone = TimeZone.getTimeZone("GMT+00");
            val sharedData = sharedDataFormat.format(Date())

            val message = MessageForServer(
                message = textMessage,
                data = date,
                sharedData = sharedData
            )

            firebase.database(REFERENCES_INIT_REALTIME_DATABASE)
                .getReference(REFERENCE_USER_CHATS)
                .child(uid).child(REFERENCE_CHATS).child(interlocutorUid)
                .child(REFERENCE_SENT_MESSAGES).child(currentTimeMillis).setValue(message)
            firebase.database(REFERENCES_INIT_REALTIME_DATABASE)
                .getReference(REFERENCE_USER_CHATS)
                .child(interlocutorUid).child(REFERENCE_CHATS).child(uid)
                .child(REFERENCE_RECEIVED_MESSAGES).child(currentTimeMillis)
                .setValue(message)
            binding.etMessage.text.clear()
            binding.rvMessages.scrollToPosition(messageAdapter.itemCount-1)
        }
    }

    fun getMessagesFromFirebaseAndAddToRecyclerView(
        firebase: Firebase,
        chatViewModel: ChatViewModel,
        messageAdapter: MessageAdapter,
        binding: FragmentChatBinding
    ) {
        val myUid = firebase.auth.currentUser?.uid.toString()
        val interlocutorUid = chatViewModel.userUid.value.toString()

        firebase.database(REFERENCES_INIT_REALTIME_DATABASE)
            .getReference(REFERENCE_USER_CHATS).child(myUid)
            .child(REFERENCE_CHATS).child(interlocutorUid).child(REFERENCE_SENT_MESSAGES)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val message = snapshot.getValue(MessageForClient::class.java)
                    message?.isSentMessage = true
                    message?.let { messageAdapter.addData(it) }
                    binding.rvMessages.scrollToPosition(messageAdapter.itemCount-1)
                }
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildRemoved(snapshot: DataSnapshot) {
                    val message = snapshot.getValue(MessageForClient::class.java)
                    message?.isSentMessage = true
                    message?.let { messageAdapter.removeData(it) }
                }
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {}
            })

        firebase.database(REFERENCES_INIT_REALTIME_DATABASE)
            .getReference(REFERENCE_USER_CHATS).child(myUid)
            .child(REFERENCE_CHATS).child(interlocutorUid).child(REFERENCE_RECEIVED_MESSAGES)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val message = snapshot.getValue(MessageForClient::class.java)
                    message?.isReceivedMessage = true
                    message?.let { messageAdapter.addData(it) }
                    binding.rvMessages.scrollToPosition(messageAdapter.itemCount-1)
                }
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildRemoved(snapshot: DataSnapshot) {
                    val message = snapshot.getValue(MessageForClient::class.java)
                    message?.isReceivedMessage = true
                    message?.let { messageAdapter.removeData(it) }
                }
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {}
            })
    }

}