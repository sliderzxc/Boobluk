package com.test.boobluk.utils.binding

import android.util.Log
import android.view.View
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.ChatAdapter
import com.test.boobluk.adapter.UserAdapter
import com.test.boobluk.databinding.FragmentAddNewChatBinding
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.databinding.FragmentListOfChatsBinding
import com.test.boobluk.utils.constants.Constants

fun hideEditProfileFragmentDesignAndShowProgressBar(
    binding: FragmentEditProfileBinding
) {
    binding.tvUploadNewAvatar.visibility = View.GONE
    binding.ivUserAvatar.visibility = View.GONE
    binding.textInputLayoutBio.visibility = View.GONE
    binding.rgChooseGender.visibility = View.GONE
    binding.tvGender.visibility = View.GONE
    binding.textInputLayoutUsername.visibility = View.GONE
    binding.textInputLayoutEmail.visibility = View.GONE
    binding.textInputLayoutPassword.visibility = View.GONE
    binding.pbDownloadData.visibility = View.VISIBLE
}

fun hideProgressBarAndShowProfileFragmentDesign(
    binding: FragmentEditProfileBinding
) {
    binding.tvUploadNewAvatar.visibility = View.VISIBLE
    binding.ivUserAvatar.visibility = View.VISIBLE
    binding.textInputLayoutBio.visibility = View.VISIBLE
    binding.rgChooseGender.visibility = View.VISIBLE
    binding.tvGender.visibility = View.VISIBLE
    binding.textInputLayoutUsername.visibility = View.VISIBLE
    binding.textInputLayoutEmail.visibility = View.VISIBLE
    binding.textInputLayoutPassword.visibility = View.VISIBLE
    binding.pbDownloadData.visibility = View.GONE
}

fun UserAdapter.checkIfRecyclerViewIsEmptyForAddNewChatFragment(binding: FragmentAddNewChatBinding) {
    if (this.itemCount > 0) {
        binding.progressBar.visibility = View.GONE
    } else {
        binding.progressBar.visibility = View.VISIBLE
    }
}

fun ChatAdapter.checkIfRecycleViewIsEmpty(
    binding: FragmentListOfChatsBinding,
) {
    if (this.itemCount > 0) {
        binding.tvYouDoNotHaveAnyChats.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    } else {
        binding.tvYouDoNotHaveAnyChats.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }
}