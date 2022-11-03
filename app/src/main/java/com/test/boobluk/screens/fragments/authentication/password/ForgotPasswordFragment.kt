package com.test.boobluk.screens.fragments.authentication.password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentForgotPasswordBinding
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
    }

    private fun sendInstructionsClickListener() {
        binding.btnSendInstructions.setOnClickListener {
            val email = binding.etEmail.text.toString()

            if (binding.etEmail.text.isNullOrEmpty()) {
                binding.textInputLayoutEmail.helperText = "Email is empty"
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                MotionToast.darkColorToast(
                    requireActivity(),
                    null,
                    "Please check your email to get instructions",
                    MotionToastStyle.INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    null
                )
            }
        }
    }
}