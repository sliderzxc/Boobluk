package com.test.boobluk.screens.fragments.authentication.password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentForgotPasswordBinding
import com.test.boobluk.helper.constants.Constants.EMAIL_ADDRESS_IS_INCORRECT
import com.test.boobluk.helper.constants.Constants.EMAIL_WAS_NOT_FOUND
import com.test.boobluk.helper.constants.Constants.EMAIL_WAS_NOT_FOUND_INFO
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class ForgotPasswordFragment : Fragment() {
    private val binding by lazy { FragmentForgotPasswordBinding.inflate(layoutInflater) }
    private var auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        sendInstructionsClickListener()
        doOnTextsChanges()
    }

    private fun doOnTextsChanges() {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutEmail.error = null
        }
    }

    private fun sendInstructionsClickListener() {
        binding.btnSendInstructions.setOnClickListener {
            val email = binding.etEmail.text

            if (email.isNullOrEmpty()) {
                binding.textInputLayoutEmail.error = getString(R.string.email_is_empty)
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email.toString()).addOnSuccessListener {
                MotionToast.darkColorToast(
                    requireActivity(),
                    null,
                    "Please check your email to get instructions",
                    MotionToastStyle.INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    null
                )
            }.addOnFailureListener {
                val exception = it.message.toString()

                if (exception == EMAIL_ADDRESS_IS_INCORRECT) {
                    binding.textInputLayoutEmail.error = EMAIL_ADDRESS_IS_INCORRECT
                    return@addOnFailureListener
                }

                if (exception == EMAIL_WAS_NOT_FOUND_INFO) {
                    binding.textInputLayoutEmail.error = EMAIL_WAS_NOT_FOUND
                    return@addOnFailureListener
                }
            }
        }
    }
}