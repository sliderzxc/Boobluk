package com.test.boobluk.screens.fragments.chat

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.MessageAdapter
import com.test.boobluk.databinding.FragmentChatBinding
import com.test.boobluk.firebase.chat.ChatFirebaseHelper
import com.test.boobluk.utils.navigation.goToListOfChatsFragment

class ChatViewModel(
    val chatFirebaseHelper: ChatFirebaseHelper
) : ViewModel() {

    private val _userUid = MutableLiveData<String>()
    var userUid: LiveData<String> = _userUid

    private val _username = MutableLiveData<String>()
    var username: LiveData<String> = _username

    fun changeUserUid(newUserUid: String) {
        _userUid.value = newUserUid
    }

    fun changeUsername(username: String) {
        _username.value = username
    }

    fun getUserDataAndUpdateDesign(
        firebase: Firebase,
        binding: FragmentChatBinding
    ) {
        chatFirebaseHelper.getUserDataAndUpdateDesign(
            interlocutorUid = userUid.value.toString(),
            firebase = firebase,
            binding = binding
        )
    }

    fun sendMessage(
        firebase: Firebase,
        binding: FragmentChatBinding,
        messageAdapter: MessageAdapter
    ) {
        chatFirebaseHelper.sendMessage(
            firebase = firebase,
            binding = binding,
            chatViewModel = this,
            messageAdapter = messageAdapter
        )
    }

    fun onBackPressedClickListeners(
        fragmentActivity: FragmentActivity,
        fragment: ChatFragment,
        binding: FragmentChatBinding
    ) {
        fragmentActivity
            .onBackPressedDispatcher
            .addCallback(fragment.viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    fragment.goToListOfChatsFragment()
                }
            })
        binding.btnBack.setOnClickListener {
            fragment.goToListOfChatsFragment()
        }
    }

    fun getMessagesFromFirebaseAndAddToRecyclerView(
        firebase: Firebase,
        messageAdapter: MessageAdapter,
        binding: FragmentChatBinding
    ) {
        chatFirebaseHelper.getMessagesFromFirebaseAndAddToRecyclerView(
            firebase = firebase,
            chatViewModel = this,
            messageAdapter = messageAdapter,
            binding = binding
        )
    }

}