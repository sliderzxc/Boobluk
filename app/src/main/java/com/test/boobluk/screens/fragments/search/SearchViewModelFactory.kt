package com.test.boobluk.screens.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.search.SearchFirebaseHelper
import com.test.boobluk.interfaces.search.SearchFirebaseInterface
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    val searchFirebaseInterface: SearchFirebaseInterface
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(
            searchFirebaseInterface = searchFirebaseInterface
        ) as T
    }
}