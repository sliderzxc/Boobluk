package com.test.boobluk.interfaces.search

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentSearchBinding

interface SearchFirebaseInterface {

    fun initCardStackView(
        firebase: Firebase,
        context: Context,
        binding: FragmentSearchBinding
    )

}