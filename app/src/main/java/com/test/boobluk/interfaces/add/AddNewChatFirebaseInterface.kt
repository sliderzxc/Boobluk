package com.test.boobluk.interfaces.add

import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.UserAdapter
import com.test.boobluk.databinding.FragmentAddNewChatBinding

interface AddNewChatFirebaseInterface {

    fun getUsersBySearch(
        firebase: Firebase,
        binding: FragmentAddNewChatBinding,
        userAdapter: UserAdapter
    )

    fun getAllUsers(
        firebase: Firebase,
        binding: FragmentAddNewChatBinding,
        userAdapter: UserAdapter
    )
}