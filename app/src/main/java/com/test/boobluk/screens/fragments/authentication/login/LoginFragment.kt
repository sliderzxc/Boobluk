package com.test.boobluk.screens.fragments.authentication.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.helper.constants.Constants.EMAIL_IS_INCORRECT
import com.test.boobluk.helper.constants.Constants.EMAIL_WAS_NOT_FOUND
import com.test.boobluk.helper.constants.Constants.EMAIL_WAS_NOT_FOUND_INFO
import com.test.boobluk.helper.constants.Constants.PASSWORD_IS_INCORRECT
import com.test.boobluk.helper.constants.Constants.PASSWORD_IS_INCORRECT_INFO
import com.test.boobluk.helper.navigation.goToForgotPasswordFragment
import com.test.boobluk.helper.navigation.goToMainFragment
import com.test.boobluk.helper.navigation.goToRegisterFragment
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class LoginFragment : Fragment() {
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private var auth = Firebase.auth

    override fun onStart() {
        super.onStart()
        checkIfUserLoginAndConfirmedEmail()
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
        loginOnClickListener()
        signUpClickListener()
        forgotPasswordClickListener()
    }

    private fun forgotPasswordClickListener() {
        binding.tvForgotPassword.setOnClickListener {
            goToForgotPasswordFragment()
        }
    }

    private fun loginOnClickListener() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (binding.etEmail.text.isNullOrEmpty()) {
                binding.textInputLayoutPassword.helperText = null
                binding.textInputLayoutEmail.helperText = "Email is empty"
                return@setOnClickListener
            }

            if (binding.etPassword.text.isNullOrEmpty()) {
                binding.textInputLayoutEmail.helperText = null
                binding.textInputLayoutPassword.helperText = "Password is empty"
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (auth.currentUser?.isEmailVerified == true) {
                    goToMainFragment()
                }
            }.addOnFailureListener {
                Log.d("MyLog", it.message.toString())
                if (it.message.toString() == PASSWORD_IS_INCORRECT_INFO) {
                    MotionToast.darkColorToast(
                        requireActivity(),
                        null,
                        PASSWORD_IS_INCORRECT,
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        null
                    )
                }

                if (it.message.toString() == EMAIL_IS_INCORRECT) {
                    MotionToast.darkColorToast(
                        requireActivity(),
                        null,
                        EMAIL_IS_INCORRECT,
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        null
                    )
                }

                if (it.message.toString() == EMAIL_WAS_NOT_FOUND_INFO) {
                    MotionToast.darkColorToast(
                        requireActivity(),
                        null,
                        EMAIL_WAS_NOT_FOUND,
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        null
                    )
                }
            }
        }
    }

    private fun signUpClickListener() {
        binding.tvSignUp.setOnClickListener {
            goToRegisterFragment()
        }
    }

    private fun checkIfUserLoginAndConfirmedEmail() {
        if (auth.currentUser != null && auth.currentUser?.isEmailVerified == true) {
            goToMainFragment()
        }
    }
}