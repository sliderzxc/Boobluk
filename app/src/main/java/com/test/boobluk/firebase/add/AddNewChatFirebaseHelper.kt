package com.test.boobluk.firebase.add

import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.UserAdapter
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentAddNewChatBinding
import com.test.boobluk.interfaces.add.AddNewChatFirebaseInterface
import com.test.boobluk.utils.binding.checkIfRecyclerViewIsEmptyForAddNewChatFragment
import com.test.boobluk.utils.constants.Constants

class AddNewChatFirebaseHelper : AddNewChatFirebaseInterface {

    override fun getUsersBySearch(
        firebase: Firebase,
        binding: FragmentAddNewChatBinding,
        userAdapter: UserAdapter
    ) {
        val userUid = firebase.auth.currentUser?.uid.toString()
        binding.etUsername.doOnTextChanged { text, _, _, _ ->
            val listOfUsers = mutableListOf<UserInfo>()
            firebase.firestore.collection(Constants.REFERENCE_USER_INFO)
                .get()
                .addOnSuccessListener { users ->
                    users.forEach { user ->
                        val currentUser = user.toObject<UserInfo>()
                        if (currentUser.uid != userUid && currentUser.username?.contains(text.toString()) == true) {
                            listOfUsers.add(currentUser)
                        }
                    }
                    userAdapter.setNewList(listOfUsers)
                    userAdapter.checkIfRecyclerViewIsEmptyForAddNewChatFragment(binding = binding)
                }
        }
    }

    override fun getAllUsers(
        firebase: Firebase,
        binding: FragmentAddNewChatBinding,
        userAdapter: UserAdapter
    ) {
        val userUid = firebase.auth.currentUser?.uid.toString()
        val listOfUsers = mutableListOf<UserInfo>()
        firebase.firestore.collection(Constants.REFERENCE_USER_INFO)
            .get()
            .addOnSuccessListener { users ->
                users.forEach { user ->
                    val currentUser = user.toObject<UserInfo>()
                    if (currentUser.uid != userUid) {
                        listOfUsers.add(currentUser)
                    }
                }
                userAdapter.setNewList(listOfUsers)
                userAdapter.checkIfRecyclerViewIsEmptyForAddNewChatFragment(binding = binding)
            }
    }

}