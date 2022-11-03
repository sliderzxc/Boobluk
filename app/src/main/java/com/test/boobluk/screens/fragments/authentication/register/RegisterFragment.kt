package com.test.boobluk.screens.fragments.authentication.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.helper.constants.Constants.EMAIL_ADDRESS_IS_BUSY
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
    }

    private fun registerOnClickListener() {
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (binding.etEmail.text.isNullOrEmpty()) {
                binding.textInputLayoutConfirmPassword.helperText = null
                binding.textInputLayoutPassword.helperText = null
                binding.textInputLayoutEmail.helperText = "Email is empty"
                return@setOnClickListener
            }

            if (binding.etPassword.text.isNullOrEmpty()) {
                binding.textInputLayoutConfirmPassword.helperText = null
                binding.textInputLayoutEmail.helperText = null
                binding.textInputLayoutPassword.helperText = "Password is empty"
                return@setOnClickListener
            }

            if (binding.etPassword.text!!.length < 6) {
                binding.textInputLayoutConfirmPassword.helperText = null
                binding.textInputLayoutEmail.helperText = null
                binding.textInputLayoutPassword.helperText = "Password is too short"
                return@setOnClickListener
            }

            if (binding.etConfirmPassword.text.isNullOrEmpty()) {
                binding.textInputLayoutPassword.helperText = null
                binding.textInputLayoutEmail.helperText = null
                binding.textInputLayoutConfirmPassword.helperText = "Password is empty"
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                binding.textInputLayoutEmail.helperText = null
                binding.textInputLayoutPassword.helperText = null
                binding.textInputLayoutConfirmPassword.helperText = "Password don't match"
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                auth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
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
                if (it.message.toString() == EMAIL_ADDRESS_IS_BUSY) {
                    MotionToast.darkColorToast(
                        requireActivity(),
                        null,
                        EMAIL_ADDRESS_IS_BUSY,
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        null
                    )
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