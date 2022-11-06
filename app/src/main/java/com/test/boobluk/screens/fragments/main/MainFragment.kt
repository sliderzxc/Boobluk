package com.test.boobluk.screens.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.test.boobluk.app.App
import com.test.boobluk.data.entities.User
import com.test.boobluk.databinding.FragmentMainBinding
import com.test.boobluk.utils.navigation.goToLoginFragment

class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val auth = Firebase.auth
    private val database = Firebase.firestore

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
        signOutClickListener()
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun signOutClickListener() {
        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            goToLoginFragment()
        }
    }

}