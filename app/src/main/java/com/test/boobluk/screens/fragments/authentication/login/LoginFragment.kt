package com.test.boobluk.screens.fragments.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.helper.constants.Constants.EMAIL_ADDRESS_IS_INCORRECT
import com.test.boobluk.helper.constants.Constants.EMAIL_WAS_NOT_FOUND
import com.test.boobluk.helper.constants.Constants.EMAIL_WAS_NOT_FOUND_INFO
import com.test.boobluk.helper.constants.Constants.PASSWORD_IS_INCORRECT
import com.test.boobluk.helper.constants.Constants.PASSWORD_IS_INCORRECT_INFO
import com.test.boobluk.helper.navigation.goToForgotPasswordFragment
import com.test.boobluk.helper.navigation.goToMainFragment
import com.test.boobluk.helper.navigation.goToRegisterFragment

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
        doOnTextsChanges()
    }

    private fun doOnTextsChanges() {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutEmail.error = null
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutPassword.error = null
        }
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
                binding.textInputLayoutPassword.error = null
                binding.textInputLayoutEmail.error = getString(R.string.email_is_empty)
                return@setOnClickListener
            }

            if (binding.etPassword.text.isNullOrEmpty()) {
                binding.textInputLayoutEmail.error = null
                binding.textInputLayoutPassword.error = getString(R.string.password_is_empty)
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                if (auth.currentUser?.isEmailVerified == true) {
                    goToMainFragment()
                }
            }.addOnFailureListener {
                val exception = it.message.toString()

                if (exception == PASSWORD_IS_INCORRECT_INFO) {
                    binding.textInputLayoutPassword.error = PASSWORD_IS_INCORRECT
                    binding.textInputLayoutEmail.error = null
                    return@addOnFailureListener
                }

                if (exception == EMAIL_ADDRESS_IS_INCORRECT) {
                    binding.textInputLayoutPassword.error = null
                    binding.textInputLayoutEmail.error = EMAIL_ADDRESS_IS_INCORRECT
                    return@addOnFailureListener
                }

                if (exception == EMAIL_WAS_NOT_FOUND_INFO) {
                    binding.textInputLayoutPassword.error = null
                    binding.textInputLayoutEmail.error = EMAIL_WAS_NOT_FOUND
                    return@addOnFailureListener
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