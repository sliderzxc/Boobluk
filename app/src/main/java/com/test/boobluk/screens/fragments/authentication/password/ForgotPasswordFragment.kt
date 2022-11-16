package com.test.boobluk.screens.fragments.authentication.password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentForgotPasswordBinding
import javax.inject.Inject

class ForgotPasswordFragment : Fragment() {
    private val binding by lazy { FragmentForgotPasswordBinding.inflate(layoutInflater) }
    @Inject
    lateinit var forgotPasswordViewModelFactory: ForgotPasswordViewModelFactory
    private val forgotPasswordViewModel: ForgotPasswordViewModel by activityViewModels { forgotPasswordViewModelFactory }
    private val auth = Firebase.auth

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
        sendInstructionsClickListener()
        doOnTextsChanged()
        buttonBackClickListener()
    }

    private fun buttonBackClickListener() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun doOnTextsChanged() {
        forgotPasswordViewModel.doOnTextChanged(binding = binding)
    }

    private fun sendInstructionsClickListener() {
        binding.btnSendInstructions.setOnClickListener {
            forgotPasswordViewModel.sendPasswordResetAndValidEditTexts(
                binding = binding,
                forgotPasswordFragment = this,
                auth = auth
            )
        }
    }
}