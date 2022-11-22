package com.test.boobluk.firebase.chat

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
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
import com.test.boobluk.data.entities.Message
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentChatBinding
import com.test.boobluk.screens.fragments.chat.ChatViewModel
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.constants.Constants.REFERENCE_DATA
import com.test.boobluk.utils.constants.Constants.REFERENCE_INIT_REALTIME_DATABASE
import com.test.boobluk.utils.constants.Constants.REFERENCE_LAST_EDIT_MESSAGE
import com.test.boobluk.utils.constants.Constants.REFERENCE_CHATS
import com.test.boobluk.utils.constants.Constants.REFERENCE_RECEIVED_MESSAGES
import com.test.boobluk.utils.constants.Constants.REFERENCE_SENT_MESSAGES
import com.test.boobluk.utils.constants.Constants.REFERENCE_USERS_DATA
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

            val textMessage = binding.etMessage.text.toString().trim()
            if (textMessage.isEmpty()) {
                return@setOnClickListener
            }

            val currentTimeMillis = System.currentTimeMillis().toString()
            val dateFormat = SimpleDateFormat("HH:mm:ss d.MM.yyyy")
            val date = dateFormat.format(Date())

            val sharedDataFormat = SimpleDateFormat("HH:mm:ss d.MM.yyyy")
            sharedDataFormat.timeZone = TimeZone.getTimeZone("GMT+00");
            val sharedData = sharedDataFormat.format(Date())

            val message = Message(
                message = textMessage,
                data = date,
                sharedData = sharedData,
                sharedDataForSort = currentTimeMillis
            )

            firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
                .getReference(REFERENCE_USER_CHATS)
                .child(uid).child(REFERENCE_CHATS).child(interlocutorUid)
                .child(REFERENCE_SENT_MESSAGES).child(currentTimeMillis)
                .setValue(message.copy(isSentMessage = true))
            firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
                .getReference(REFERENCE_USER_CHATS)
                .child(interlocutorUid).child(REFERENCE_CHATS).child(uid)
                .child(REFERENCE_RECEIVED_MESSAGES).child(currentTimeMillis)
                .setValue(message.copy(isReceivedMessage = true))
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

        firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
            .getReference(REFERENCE_USER_CHATS).child(myUid)
            .child(REFERENCE_CHATS).child(interlocutorUid).child(REFERENCE_SENT_MESSAGES)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val message = snapshot.getValue(Message::class.java)
                    message?.let { messageAdapter.addData(it) }
                    binding.rvMessages.scrollToPosition(messageAdapter.itemCount-1)
                }
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val newMessage = snapshot.getValue(Message::class.java)
                    val oldMessage = chatViewModel.oldMessage.value

                    firebase.database(REFERENCE_INIT_REALTIME_DATABASE).getReference(REFERENCE_USERS_DATA)
                        .child(myUid).child(REFERENCE_DATA).child(REFERENCE_LAST_EDIT_MESSAGE)
                        .setValue(oldMessage)

                    if (oldMessage != null && newMessage != null) {
                        messageAdapter.updateData(oldMessage, newMessage)
                    }
                    binding.rvMessages.scrollToPosition(messageAdapter.itemCount-1)
                }
                override fun onChildRemoved(snapshot: DataSnapshot) {
                    val message = snapshot.getValue(Message::class.java)
                    message?.let { messageAdapter.removeData(it) }
                }
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {}
            })

        firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
            .getReference(REFERENCE_USER_CHATS).child(myUid)
            .child(REFERENCE_CHATS).child(interlocutorUid).child(REFERENCE_RECEIVED_MESSAGES)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val message = snapshot.getValue(Message::class.java)
                    message?.let { messageAdapter.addData(it) }
                    binding.rvMessages.scrollToPosition(messageAdapter.itemCount-1)
                }
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val newMessage = snapshot.getValue(Message::class.java)
                    newMessage?.let { chatViewModel.changeNewMessage(newMessage = it) }
                }
                override fun onChildRemoved(snapshot: DataSnapshot) {
                    val message = snapshot.getValue(Message::class.java)
                    message?.let { messageAdapter.removeData(it) }
                }
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {}
            })
    }

    fun getDataChangesAndUpdateMessageAdapter(
        firebase: Firebase,
        messageAdapter: MessageAdapter,
        chatViewModel: ChatViewModel,
        lifecycleOwner: LifecycleOwner
    ) {
        val interlocutorUid = chatViewModel.userUid.value.toString()

        chatViewModel.newMessage.observe(lifecycleOwner) { newMessage ->
            firebase.database(REFERENCE_INIT_REALTIME_DATABASE).getReference(REFERENCE_USERS_DATA)
                .child(interlocutorUid).child(REFERENCE_DATA).child(REFERENCE_LAST_EDIT_MESSAGE).get().addOnSuccessListener {
                    val oldMessageFromFirebase = it.getValue(Message::class.java)
                    oldMessageFromFirebase?.isSentMessage = null
                    oldMessageFromFirebase?.isReceivedMessage = true
                    oldMessageFromFirebase?.let { oldMessage -> messageAdapter.updateData(oldMessage, newMessage) }
                }
        }
    }

    fun deleteMessage(
        firebase: Firebase,
        chatViewModel: ChatViewModel,
        message: Message
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()
        val interlocutorUid = chatViewModel.userUid.value.toString()

        val currentTimeMillis = message.sharedDataForSort.toString()

        firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
            .getReference(REFERENCE_USER_CHATS)
            .child(uid).child(REFERENCE_CHATS).child(interlocutorUid)
            .child(REFERENCE_SENT_MESSAGES).child(currentTimeMillis).removeValue()
        firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
            .getReference(REFERENCE_USER_CHATS)
            .child(interlocutorUid).child(REFERENCE_CHATS).child(uid)
            .child(REFERENCE_RECEIVED_MESSAGES).child(currentTimeMillis).removeValue()
    }

    fun editMessage(
        firebase: Firebase,
        chatViewModel: ChatViewModel,
        message: Message,
        chatBinding: FragmentChatBinding,
    ) {
        chatBinding.btnEditMessage.setOnClickListener {
            val uid = firebase.auth.currentUser?.uid.toString()
            val interlocutorUid = chatViewModel.userUid.value.toString()

            val currentTimeMillis = message.sharedDataForSort.toString()

            chatViewModel.changeOldMessage(message)

            val newSentMessage = message.copy(message = chatBinding.etMessage.text.trim().toString())
            val newReceivedMessage = newSentMessage.copy(isSentMessage = null, isReceivedMessage = true)

            val newSentMessageHashMap = hashMapOf(
                "message" to newSentMessage.message as Any,
                "data" to newSentMessage.data,
                "sharedData" to newSentMessage.sharedData,
                "sharedDataForSort" to newSentMessage.sharedDataForSort,
                "sentMessage" to newSentMessage.isSentMessage,
                "receivedMessage" to newSentMessage.isReceivedMessage
            )

            val newReceivedMessageHashMap = hashMapOf(
                "message" to newReceivedMessage.message as Any,
                "data" to newReceivedMessage.data,
                "sharedData" to newReceivedMessage.sharedData,
                "sharedDataForSort" to newReceivedMessage.sharedDataForSort,
                "sentMessage" to newReceivedMessage.isSentMessage,
                "receivedMessage" to newReceivedMessage.isReceivedMessage
            )

            firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
                .getReference(REFERENCE_USER_CHATS)
                .child(uid).child(REFERENCE_CHATS).child(interlocutorUid)
                .child(REFERENCE_SENT_MESSAGES).child(currentTimeMillis).updateChildren(newSentMessageHashMap)
            firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
                .getReference(REFERENCE_USER_CHATS)
                .child(interlocutorUid).child(REFERENCE_CHATS).child(uid)
                .child(REFERENCE_RECEIVED_MESSAGES).child(currentTimeMillis).updateChildren(newReceivedMessageHashMap)
            chatBinding.etMessage.text.clear()
            chatBinding.tvEditMessage.visibility = View.GONE
            chatBinding.btnEditMessage.visibility = View.GONE
            chatBinding.btnSendMessage.visibility = View.VISIBLE
        }
    }

}