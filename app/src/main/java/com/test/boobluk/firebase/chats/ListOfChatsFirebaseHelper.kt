package com.test.boobluk.firebase.chats

import android.view.View
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.ChatAdapter
import com.test.boobluk.data.entities.chat.AboutChat
import com.test.boobluk.data.entities.messages.LastMessage
import com.test.boobluk.databinding.FragmentListOfChatsBinding
import com.test.boobluk.interfaces.chats.ListOfChatsFirebaseInterface
import com.test.boobluk.utils.binding.checkIfRecycleViewIsEmpty
import com.test.boobluk.utils.constants.Constants.REFERENCE_CHATS
import com.test.boobluk.utils.constants.Constants.REFERENCE_INIT_REALTIME_DATABASE
import com.test.boobluk.utils.constants.Constants.REFERENCE_LAST_MESSAGE
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_CHATS
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO

class ListOfChatsFirebaseHelper : ListOfChatsFirebaseInterface {

    override fun getAllChats(
        binding:FragmentListOfChatsBinding,
        firebase: Firebase,
        chatAdapter: ChatAdapter
    ) {
        binding.progressBar.visibility = View.VISIBLE
        val uid = firebase.auth.currentUser?.uid.toString()

        firebase.database(REFERENCE_INIT_REALTIME_DATABASE).getReference(REFERENCE_USER_CHATS)
            .child(uid).child(REFERENCE_CHATS).addChildEventListener(object: ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val interlocutorUid = snapshot.key.toString()
                    firebase.firestore.collection(REFERENCE_USER_INFO)
                        .document(interlocutorUid).get()
                        .addOnSuccessListener { documentSnapshot ->
                            val aboutChat = documentSnapshot.toObject<AboutChat>()
                            firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
                                .getReference(REFERENCE_USER_CHATS).child(uid)
                                .child(REFERENCE_CHATS).child(interlocutorUid).child(REFERENCE_LAST_MESSAGE)
                                .get().addOnSuccessListener { dataSnapshot ->
                                    val lastMessage = dataSnapshot.getValue(LastMessage::class.java)
                                    aboutChat?.lastMessage = lastMessage
                                    aboutChat?.let { aboutChatItem -> chatAdapter.addNewItem(aboutChatItem) }
                                    chatAdapter.checkIfRecycleViewIsEmpty(
                                        binding = binding,
                                        firebase = firebase
                                    )
                                }
                        }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    val interlocutorUid = snapshot.key.toString()
                    firebase.firestore.collection(REFERENCE_USER_INFO)
                        .document(interlocutorUid).get()
                        .addOnSuccessListener { documentSnapshot ->
                            val aboutChat = documentSnapshot.toObject<AboutChat>()
                            firebase.database(REFERENCE_INIT_REALTIME_DATABASE)
                                .getReference(REFERENCE_USER_CHATS).child(uid)
                                .child(REFERENCE_CHATS).child(interlocutorUid).child(REFERENCE_LAST_MESSAGE)
                                .get().addOnSuccessListener { dataSnapshot ->
                                    val lastMessage = dataSnapshot.getValue(LastMessage::class.java)
                                    aboutChat?.lastMessage = lastMessage
                                    aboutChat?.let { aboutChatItem -> chatAdapter.removeItem(aboutChatItem) }
                                    chatAdapter.checkIfRecycleViewIsEmpty(
                                        binding = binding,
                                        firebase = firebase
                                    )
                                }
                        }
                }
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {}
            })
    }

    override fun deleteChat(
        firebase: Firebase,
        interlocutorUid: String
    ) {
        val userUid = firebase.auth.currentUser?.uid.toString()
        firebase.database(REFERENCE_INIT_REALTIME_DATABASE).getReference(REFERENCE_USER_CHATS)
            .child(userUid).child(REFERENCE_CHATS).child(interlocutorUid).removeValue()
        firebase.database(REFERENCE_INIT_REALTIME_DATABASE).getReference(REFERENCE_USER_CHATS)
            .child(interlocutorUid).child(REFERENCE_CHATS).child(userUid).removeValue()
    }

}