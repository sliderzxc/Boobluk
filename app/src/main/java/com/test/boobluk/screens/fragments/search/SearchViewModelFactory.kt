package com.test.boobluk.screens.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.firebase.search.SearchFirebaseHelper
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    val searchFirebaseHelper: SearchFirebaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(
            searchFirebaseHelper = searchFirebaseHelper
        ) as T
    }
}