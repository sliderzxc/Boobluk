package com.test.boobluk.screens.fragments.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.adapter.UserAdapter
import com.test.boobluk.app.App
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.FragmentAddNewChatBinding
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddNewChatFragment : Fragment() {
    private val binding by lazy { FragmentAddNewChatBinding.inflate(layoutInflater) }
    private val firebase = Firebase
    private val userAdapter = UserAdapter()

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
    }

    private fun initRecyclerViewUser() {
        binding.rvUsers.adapter = userAdapter
    }

    private fun getUsersBySearch() {
        binding.etUsername.doOnTextChanged { text, _, _, _ ->
            val listOfUsers = mutableListOf<UserInfo>()
            firebase.firestore.collection(REFERENCE_USER_INFO)
                .get()
                .addOnSuccessListener { users ->
                    users.forEach { user ->
                        val currentUser = user.toObject<UserInfo>()
                        if (currentUser.username?.contains(text.toString()) == true) {
                            listOfUsers.add(currentUser)
                        }
                    }
                    userAdapter.setNewList(listOfUsers)
                }
        }
    }

    private fun getAllUsers() {
        val listOfUsers = mutableListOf<UserInfo>()
        firebase.firestore.collection(REFERENCE_USER_INFO)
            .get()
            .addOnSuccessListener { users ->
                users.forEach { user ->
                    val currentUser = user.toObject<UserInfo>()
                    listOfUsers.add(currentUser)
                }
                userAdapter.setNewList(listOfUsers)
            }
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
