package com.test.boobluk.screens.fragments.chat

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.MessageAdapter
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentChatBinding
import com.test.boobluk.network.viewmodel.NotificationViewModel
import com.test.boobluk.network.viewmodel.NotificationViewModelFactory
import javax.inject.Inject

class ChatFragment : Fragment() {
    private val binding by lazy { FragmentChatBinding.inflate(layoutInflater) }
    @Inject
    lateinit var chatViewModelFactory: ChatViewModelFactory
    private val chatViewModel: ChatViewModel by activityViewModels { chatViewModelFactory }
    @Inject
    lateinit var notificationViewModelFactory: NotificationViewModelFactory
    private val notificationViewModel: NotificationViewModel by activityViewModels { notificationViewModelFactory }
    private lateinit var messageAdapter: MessageAdapter
    private val firebase = Firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        inject()
        initRecyclerView()
        onBackPressedClickListeners()
        getUserDataAndUpdateDesign()
        getMessagesFromFirebaseAndAddToRecyclerView()
        checkIfExistsAndClearNotificationsInThisChat()
        changeInChatWithInFirebase()
        sendMessageWhenPressedButtonSend()
    }

    private fun getMessagesFromFirebaseAndAddToRecyclerView() {
        chatViewModel.getMessagesFromFirebaseAndAddToRecyclerView(
            firebase = firebase,
            messageAdapter = messageAdapter,
            binding = binding
        )
    }

    private fun getUserDataAndUpdateDesign() {
        chatViewModel.getUserDataAndUpdateDesign(
            firebase = firebase,
            binding = binding
        )
    }

    private fun changeInChatWithInFirebase() {
        chatViewModel.changeInChatWithInFirebase(firebase)
    }

    private fun sendMessageWhenPressedButtonSend() {
        chatViewModel.sendMessage(
            firebase = firebase,
            binding = binding,
            messageAdapter = messageAdapter,
            notificationViewModel = notificationViewModel
        )
    }

    private fun onBackPressedClickListeners() {
        chatViewModel.onBackPressedClickListeners(
            fragmentActivity = requireActivity(),
            fragment = this,
            binding = binding
        )
    }

    private fun initRecyclerView() {
        val interlocutorUid = chatViewModel.username.value.toString()
        messageAdapter = MessageAdapter(interlocutorUsername = interlocutorUid) {
            message -> chatViewModel.showEditMessageBottomDialog(
                firebase = firebase,
                requireActivity(),
                requireContext(),
                message = message,
                chatBinding = binding
            )
        }
        messageAdapter.clearAllMessages()
        binding.rvMessages.adapter = messageAdapter
        binding.rvMessages.scrollToPosition(messageAdapter.itemCount-1)
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun checkIfExistsAndClearNotificationsInThisChat() {
        chatViewModel.checkIfExistsAndClearNotificationsInThisChat(
            firebase = firebase,
            context = requireContext()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        messageAdapter.clearAllMessages()
        chatViewModel.clearInChatWithInFirebase(firebase)
    }

}