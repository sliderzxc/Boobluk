package com.test.boobluk.screens.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.UserAdapter
import com.test.boobluk.app.App
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentAddNewChatBinding
import com.test.boobluk.screens.fragments.chat.ChatViewModel
import com.test.boobluk.screens.fragments.chat.ChatViewModelFactory
import com.test.boobluk.utils.binding.checkIfRecyclerViewIsEmptyForAddNewChatFragment
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO
import com.test.boobluk.utils.navigation.goToChatFragment
import javax.inject.Inject

class AddNewChatFragment : Fragment() {
    private val binding by lazy { FragmentAddNewChatBinding.inflate(layoutInflater) }
    private val userAdapter = UserAdapter { newUserUid -> clickOnItemUserListener(newUserUid) }
    @Inject
    lateinit var addNewChatViewModelFactory: AddNewChatViewModelFactory
    private val addNewChatViewModel: AddNewChatViewModel by activityViewModels { addNewChatViewModelFactory }
    @Inject
    lateinit var chatViewModelFactory: ChatViewModelFactory
    private val chatViewModel: ChatViewModel by activityViewModels { chatViewModelFactory }
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
        initRecyclerViewUser()
        getAllUsers()
        getUsersBySearch()
        buttonBackClickListener()
        checkIfRecyclerViewIsEmptyForAddNewChatFragment()
    }

    private fun checkIfRecyclerViewIsEmptyForAddNewChatFragment() {
        userAdapter.checkIfRecyclerViewIsEmptyForAddNewChatFragment(binding = binding)
    }

    private fun clickOnItemUserListener(newUserUid: String) {
        chatViewModel.changeUserUid(newUserUid = newUserUid)
        goToChatFragment()
    }

    private fun initRecyclerViewUser() {
        binding.rvUsers.adapter = userAdapter
    }

    private fun getAllUsers() {
        addNewChatViewModel.getAllUsers(
            firebase = firebase,
            binding = binding,
            userAdapter = userAdapter
        )
    }

    private fun getUsersBySearch() {
        addNewChatViewModel.getUsersBySearch(
            firebase = firebase,
            binding = binding,
            userAdapter = userAdapter
        )
    }

    private fun buttonBackClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }
}
