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
import com.test.boobluk.data.entities.AboutChat
import com.test.boobluk.databinding.FragmentListOfChatsBinding
import com.test.boobluk.utils.binding.checkIfRecycleViewIsEmpty
import com.test.boobluk.utils.constants.Constants

class ListOfChatsFirebaseHelper {

    fun getAllChats(
        binding:FragmentListOfChatsBinding,
        firebase: Firebase,
        chatAdapter: ChatAdapter
    ) {
        binding.progressBar.visibility = View.VISIBLE
        val userUid = firebase.auth.currentUser?.uid.toString()
        firebase.database(Constants.REFERENCE_INIT_REALTIME_DATABASE).getReference(Constants.REFERENCE_USER_CHATS)
            .child(userUid).child(Constants.REFERENCE_CHATS).addChildEventListener(object: ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val interlocutorUid = snapshot.key.toString()
                    firebase.firestore.collection(Constants.REFERENCE_USER_INFO)
                        .document(interlocutorUid).get()
                        .addOnSuccessListener { documentSnapshot ->
                            val aboutChat = documentSnapshot.toObject<AboutChat>()
                            aboutChat?.let { aboutChatItem -> chatAdapter.addNewItem(aboutChatItem) }
                            chatAdapter.checkIfRecycleViewIsEmpty(binding = binding)
                        }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    val interlocutorUid = snapshot.key.toString()
                    firebase.firestore.collection(Constants.REFERENCE_USER_INFO)
                        .document(interlocutorUid).get()
                        .addOnSuccessListener { documentSnapshot ->
                            val aboutChat = documentSnapshot.toObject<AboutChat>()
                            aboutChat?.let { aboutChatItem -> chatAdapter.removeItem(aboutChatItem) }
                            chatAdapter.checkIfRecycleViewIsEmpty(binding = binding)
                        }
                }
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {}
            })
    }

}