package com.test.boobluk.screens.fragments.authentication.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.test.boobluk.R
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentLoginBinding
import com.test.boobluk.utils.constants.Constants.EMAIL_ADDRESS_IS_INCORRECT
import com.test.boobluk.utils.constants.Constants.EMAIL_WAS_NOT_FOUND
import com.test.boobluk.utils.constants.Constants.EMAIL_WAS_NOT_FOUND_INFO
import com.test.boobluk.utils.constants.Constants.PASSWORD_IS_INCORRECT
import com.test.boobluk.utils.constants.Constants.PASSWORD_IS_INCORRECT_INFO
import com.test.boobluk.utils.navigation.goToForgotPasswordFragment
import com.test.boobluk.utils.navigation.goToMainFragment
import com.test.boobluk.utils.navigation.goToRegisterFragment
import com.test.boobluk.utils.toast.showDarkMotionColorToast
import javax.inject.Inject

class LoginFragment : Fragment() {
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory
    private val loginViewModel: LoginViewModel by activityViewModels { loginViewModelFactory }
    private val firebase = Firebase

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
        inject()
        loginOnClickListener()
        signUpClickListener()
        forgotPasswordClickListener()
        doOnTextsChanges()
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun doOnTextsChanges() {
        loginViewModel.doOnTextsChanged(binding = binding)
    }

    private fun forgotPasswordClickListener() {
        binding.tvForgotPassword.setOnClickListener {
            goToForgotPasswordFragment()
        }
    }

    private fun loginOnClickListener() {
        binding.btnLogin.setOnClickListener {
            loginViewModel.signInAndValidEditTexts(
                binding = binding,
                loginFragment = this,
                firebase = firebase
            )
        }
    }

    private fun signUpClickListener() {
        binding.tvSignUp.setOnClickListener {
            goToRegisterFragment()
        }
    }

    private fun checkIfUserLoginAndConfirmedEmail() {
        loginViewModel.checkIfUserLoginAndConfirmedEmail(
            firebase = firebase,
            loginFragment = this
        )
    }
}