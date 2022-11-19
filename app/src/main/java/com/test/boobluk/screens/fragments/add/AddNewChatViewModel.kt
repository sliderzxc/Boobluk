package com.test.boobluk.screens.fragments.add

import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.UserAdapter
import com.test.boobluk.databinding.FragmentAddNewChatBinding
import com.test.boobluk.firebase.add.AddNewChatFirebaseHelper

class AddNewChatViewModel(
    val addNewChatFirebaseHelper: AddNewChatFirebaseHelper
) : ViewModel() {

    fun getUsersBySearch(
        firebase: Firebase,
        binding: FragmentAddNewChatBinding,
        userAdapter: UserAdapter
    ) {
        addNewChatFirebaseHelper.getUsersBySearch(
            firebase = firebase,
            binding = binding,
            userAdapter = userAdapter
        )
    }

    fun getAllUsers(
        firebase: Firebase,
        binding: FragmentAddNewChatBinding,
        userAdapter: UserAdapter
    ) {
        addNewChatFirebaseHelper.getAllUsers(
            firebase = firebase,
            binding = binding,
            userAdapter = userAdapter
        )
    }
}