package com.test.boobluk.screens.fragments.chats

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.adapter.ChatAdapter
import com.test.boobluk.app.App
import com.test.boobluk.data.entities.AboutChat
import com.test.boobluk.databinding.FragmentListOfChatsBinding
import com.test.boobluk.screens.fragments.chat.ChatViewModel
import com.test.boobluk.screens.fragments.chat.ChatViewModelFactory
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.binding.checkIfRecycleViewIsEmpty
import com.test.boobluk.utils.constants.Constants.REFERENCES_INIT_REALTIME_DATABASE
import com.test.boobluk.utils.constants.Constants.REFERENCE_CHATS
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_CHATS
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO
import com.test.boobluk.utils.navigation.changeFragment
import com.test.boobluk.utils.navigation.goToAddNewFragment
import com.test.boobluk.utils.navigation.goToChatFragment
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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