package com.test.boobluk.screens.fragments.chats

import android.annotation.SuppressLint
import android.content.Context
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.adapter.ChatAdapter
import com.test.boobluk.databinding.DialogEditChatBinding
import com.test.boobluk.databinding.DialogEditMessageBinding
import com.test.boobluk.databinding.FragmentListOfChatsBinding
import com.test.boobluk.firebase.chats.ListOfChatsFirebaseHelper
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import com.test.boobluk.utils.binding.checkIfRecycleViewIsEmpty
import com.test.boobluk.utils.navigation.changeFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListOfChatsViewModel(
    val listOfChatsFirebaseHelper: ListOfChatsFirebaseHelper
) : ViewModel() {

    fun getAllChats(
        binding: FragmentListOfChatsBinding,
        firebase: Firebase,
        chatAdapter: ChatAdapter
    ) {
        listOfChatsFirebaseHelper.getAllChats(
            binding = binding,
            firebase = firebase,
            chatAdapter = chatAdapter
        )

        viewModelScope.launch {
            delay(8000)
            chatAdapter.checkIfRecycleViewIsEmpty(
                binding = binding,
                firebase = firebase
            )
        }
    }

    private fun deleteChat(
        firebase: Firebase,
        interlocutorUid: String
    ) {
        listOfChatsFirebaseHelper.deleteChat(
            firebase = firebase,
            interlocutorUid = interlocutorUid
        )
    }

    @SuppressLint("InflateParams")
    fun showEditMessageBottomDialog(
        activity: FragmentActivity,
        context: Context,
        firebase: Firebase,
        interlocutorUid: String
    ) {
        val view = activity.layoutInflater.inflate(R.layout.dialog_edit_chat, null, false)
        val dialog = BottomSheetDialog(context)
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        val dialogBinding by lazy { DialogEditChatBinding.bind(view) }

        dialogBinding.tvDeleteChat.setOnClickListener {
            deleteChat(
                firebase = firebase,
                interlocutorUid = interlocutorUid
            )
            dialog.hide()
        }

        dialog.setContentView(view)
        dialog.show()
    }

    fun bottomNavigationViewClickListener(binding: FragmentListOfChatsBinding, parentFragmentManager: FragmentManager) {
        binding.mainBottomNavigationView.menu.getItem(0).isChecked = true
        binding.mainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemSearchNavigation -> { parentFragmentManager.changeFragment(SearchFragment()) }
                R.id.itemEditProfileNavigation -> { parentFragmentManager.changeFragment(
                    EditProfileFragment()
                ) }
                R.id.itemSettingsNavigation -> { parentFragmentManager.changeFragment(
                    SettingsFragment()
                ) }
                else -> true
            }
        }
    }



}