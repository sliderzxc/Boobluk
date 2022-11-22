package com.test.boobluk.screens.fragments.chats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.ChatAdapter
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentListOfChatsBinding
import com.test.boobluk.screens.fragments.chat.ChatViewModel
import com.test.boobluk.screens.fragments.chat.ChatViewModelFactory
import com.test.boobluk.utils.navigation.goToAddNewFragment
import com.test.boobluk.utils.navigation.goToChatFragment
import javax.inject.Inject

class ListOfChatsFragment : Fragment() {
    private val binding by lazy { FragmentListOfChatsBinding.inflate(layoutInflater) }
    private val chatAdapter = ChatAdapter { uid, username -> clickOnItemChatListener(uid, username) }
    @Inject
    lateinit var chatViewModelFactory: ChatViewModelFactory
    private val chatViewModel: ChatViewModel by activityViewModels { chatViewModelFactory }
    @Inject
    lateinit var listOfChatsViewModelFactory: ListOfChatsViewModelFactory
    private val listOfChatViewModel: ListOfChatsViewModel by activityViewModels { listOfChatsViewModelFactory }
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
        initConfig()
        bottomNavigationViewClickListener()
        buttonAddNewChatClickListener()
        initRecyclerViewChat()
        getAllChats()
    }

    private fun initConfig() {
        binding.mainBottomNavigationView.background = null
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun clickOnItemChatListener(newUserUid: String, username: String) {
        chatViewModel.changeUserUid(newUserUid = newUserUid)
        chatViewModel.changeUsername(username = username)
        goToChatFragment()
    }

    private fun getAllChats() {
       listOfChatViewModel.getAllChats(
           binding = binding,
           firebase = firebase,
           chatAdapter = chatAdapter
       )
    }

    private fun initRecyclerViewChat() {
        binding.rvChats.adapter = chatAdapter
    }

    private fun buttonAddNewChatClickListener() {
        binding.itemAddChatNavigation.setOnClickListener {
            goToAddNewFragment()
        }
    }

    private fun bottomNavigationViewClickListener() {
        listOfChatViewModel.bottomNavigationViewClickListener(
            binding = binding,
            parentFragmentManager = parentFragmentManager
        )
    }

    override fun onStop() {
        super.onStop()
        chatAdapter.clearListOfChats()
    }

}