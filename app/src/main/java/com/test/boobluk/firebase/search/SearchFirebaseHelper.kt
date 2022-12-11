package com.test.boobluk.firebase.search

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.test.boobluk.databinding.FragmentSearchBinding
import com.test.boobluk.interfaces.search.SearchFirebaseInterface
import com.test.boobluk.utils.binding.CardStackView
import javax.inject.Inject

class SearchFirebaseHelper @Inject constructor(
    private val cardStackView: CardStackView
) : SearchFirebaseInterface {

    override fun initCardStackView(
        firebase: Firebase,
        context: Context,
        binding: FragmentSearchBinding
    ) {
        cardStackView.initCardStackView(
            firebase = firebase,
            context = context,
            binding = binding
        )
    }
}