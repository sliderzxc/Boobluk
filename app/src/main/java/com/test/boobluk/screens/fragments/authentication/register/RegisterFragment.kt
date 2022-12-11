package com.test.boobluk.screens.fragments.authentication.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentRegisterBinding
import com.test.boobluk.utils.navigation.goToLoginFragment
import javax.inject.Inject

class RegisterFragment : Fragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    @Inject
    lateinit var registerViewModelFactory: RegisterViewModelFactory
    private val registerViewModel: RegisterViewModel by activityViewModels { registerViewModelFactory }
    private val firebase = Firebase

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
        inject()
        registerOnClickListener()
        signInClickListener()
        doOnTextsChanges()
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun doOnTextsChanges() {
        registerViewModel.doOnTextsChanges(binding)
    }

    private fun registerOnClickListener() {
        binding.btnRegister.setOnClickListener {
            registerViewModel.createUserAndCheckValidEditTexts(this, binding, firebase)
        }
    }

    private fun signInClickListener() {
        binding.tvHaveAnAccount.setOnClickListener {
            goToLoginFragment()
        }
    }

    private fun checkIfUserRegisteredAndConfirmedEmail() {
        registerViewModel.checkIfUserRegisteredAndConfirmedEmail(firebase, this)
    }
}