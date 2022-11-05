package com.test.boobluk.screens.fragments.authentication.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.helper.constants.Constants.EMAIL_ADDRESS_IS_BUSY
import com.test.boobluk.helper.constants.Constants.EMAIL_ADDRESS_IS_INCORRECT
import com.test.boobluk.helper.navigation.goToLoginFragment
import com.test.boobluk.helper.navigation.goToMainFragment
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class RegisterFragment : Fragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    private var auth = Firebase.auth

    override fun onStart() {
        super.onStart()
        checkIfUserRegisteredAndConfirmedEmail()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        registerOnClickListener()
        signInClickListener()
        doOnTextsChanges()
    }

    private fun doOnTextsChanges() {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutEmail.error = null
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutPassword.error = null
        }
        binding.etConfirmPassword.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutConfirmPassword.error = null
        }
    }

    private fun registerOnClickListener() {
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text
            val password = binding.etPassword.text
            val confirmPassword = binding.etConfirmPassword.text

            if (email.isNullOrEmpty()) {
                binding.textInputLayoutConfirmPassword.error = null
                binding.textInputLayoutPassword.error = null
                binding.textInputLayoutEmail.error = getString(R.string.email_is_empty)
                return@setOnClickListener
            }

            if (password.isNullOrEmpty()) {
                binding.textInputLayoutConfirmPassword.error = null
                binding.textInputLayoutEmail.error = null
                binding.textInputLayoutPassword.error = getString(R.string.password_is_empty)
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.textInputLayoutConfirmPassword.error = null
                binding.textInputLayoutEmail.error = null
                binding.textInputLayoutPassword.error = getString(R.string.password_is_too_short)
                return@setOnClickListener
            }

            if (confirmPassword.isNullOrEmpty()) {
                binding.textInputLayoutPassword.error = null
                binding.textInputLayoutEmail.error = null
                binding.textInputLayoutConfirmPassword.error = getString(R.string.password_is_empty)
                return@setOnClickListener
            }

            if (password.toString() != confirmPassword.toString()) {
                binding.textInputLayoutEmail.error = null
                binding.textInputLayoutPassword.error = null
                binding.textInputLayoutConfirmPassword.error = getString(R.string.password_do_not_match)
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email.toString(), password.toString()).addOnSuccessListener {
                auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                    MotionToast.darkColorToast(
                        requireActivity(),
                        null,
                        "Please check your email to confirm your account",
                        MotionToastStyle.INFO,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        null
                    )
                }
            }.addOnFailureListener {
                val exception = it.message.toString()

                if (exception == EMAIL_ADDRESS_IS_BUSY) {
                    binding.textInputLayoutEmail.error = EMAIL_ADDRESS_IS_BUSY
                    return@addOnFailureListener
                }

                if (exception == EMAIL_ADDRESS_IS_INCORRECT) {
                    binding.textInputLayoutEmail.error = EMAIL_ADDRESS_IS_INCORRECT
                    return@addOnFailureListener
                }
            }
        }
    }

    private fun signInClickListener() {
        binding.tvSignIn.setOnClickListener {
            goToLoginFragment()
        }
    }

    private fun checkIfUserRegisteredAndConfirmedEmail() {
        if (auth.currentUser != null && auth.currentUser?.isEmailVerified == true) {
            goToMainFragment()
        }
    }
}