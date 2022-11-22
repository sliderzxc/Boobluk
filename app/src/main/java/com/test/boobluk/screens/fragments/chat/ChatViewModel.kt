package com.test.boobluk.screens.fragments.chat

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.adapter.MessageAdapter
import com.test.boobluk.data.entities.Message
import com.test.boobluk.databinding.DialogEditMessageBinding
import com.test.boobluk.databinding.FragmentChatBinding
import com.test.boobluk.firebase.chat.ChatFirebaseHelper
import com.test.boobluk.utils.navigation.goToListOfChatsFragment

class ChatViewModel(
    val chatFirebaseHelper: ChatFirebaseHelper
) : ViewModel() {

    private val _oldMessage = MutableLiveData<Message>()
    var oldMessage: LiveData<Message> = _oldMessage

    private val _newMessage = MutableLiveData<Message>()
    var newMessage: LiveData<Message> = _newMessage

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

    fun changeOldMessage(oldMessage: Message) {
        _oldMessage.value = oldMessage
    }

    fun changeNewMessage(newMessage: Message) {
        _newMessage.value = newMessage
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

    private fun deleteMessage(
        firebase: Firebase,
        message: Message
    ) {
       chatFirebaseHelper.deleteMessage(
           firebase = firebase,
           chatViewModel = this,
           message = message
       )
    }

    private fun editMessage(
        firebase: Firebase,
        message: Message,
        chatBinding: FragmentChatBinding,
    ) {
        chatFirebaseHelper.editMessage(
            firebase = firebase,
            chatViewModel = this,
            message = message,
            chatBinding = chatBinding,
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

    fun getDataChangesAndUpdateMessageAdapter(
        firebase: Firebase,
        messageAdapter: MessageAdapter,
        lifecycleOwner: LifecycleOwner
    ) {
        chatFirebaseHelper.getDataChangesAndUpdateMessageAdapter(
            firebase = firebase,
            messageAdapter = messageAdapter,
            chatViewModel = this,
            lifecycleOwner = lifecycleOwner
        )
    }

    @SuppressLint("InflateParams")
    fun showEditMessageBottomDialog(
        firebase: Firebase,
        activity: FragmentActivity,
        context: Context,
        message: Message,
        chatBinding: FragmentChatBinding,
        messageAdapter: MessageAdapter
    ) {
        val view = activity.layoutInflater.inflate(R.layout.dialog_edit_message, null, false)
        val dialog = BottomSheetDialog(context)
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        val dialogBinding by lazy { DialogEditMessageBinding.bind(view) }
        dialogBinding.tvDeleteMessage.setOnClickListener {
            deleteMessage(
                firebase = firebase,
                message = message
            )
            dialog.hide()
        }
        dialogBinding.tvEditMessage.setOnClickListener {
            chatBinding.tvEditMessage.visibility = View.VISIBLE
            chatBinding.btnSendMessage.visibility = View.GONE
            chatBinding.btnEditMessage.visibility = View.VISIBLE
            chatBinding.etMessage.setText(message.message.toString())
            editMessage(
                firebase = firebase,
                message = message,
                chatBinding = chatBinding
            )
            dialog.hide()
        }
        dialog.setContentView(view)
        dialog.show()
    }

}