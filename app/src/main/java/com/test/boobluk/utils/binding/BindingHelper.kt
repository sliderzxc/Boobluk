package com.test.boobluk.utils.binding

import android.view.View
import com.test.boobluk.databinding.FragmentEditProfileBinding

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