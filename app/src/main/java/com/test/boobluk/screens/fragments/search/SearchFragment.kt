package com.test.boobluk.screens.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentSearchBinding
import com.test.boobluk.utils.navigation.goToAddNewFragment
import javax.inject.Inject

class SearchFragment : Fragment() {
    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory
    private val searchViewModel: SearchViewModel by activityViewModels { searchViewModelFactory }

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
        initConfig()
        bottomNavigationViewClickListener()
        buttonAddNewChatClickListener()
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun initConfig() {
        binding.mainBottomNavigationView.background = null
    }

    private fun buttonAddNewChatClickListener() {
        binding.itemAddChatNavigation.setOnClickListener {
            goToAddNewFragment()
        }
    }

    private fun bottomNavigationViewClickListener() {
        searchViewModel.bottomNavigationViewClickListener(
            binding = binding,
            parentFragmentManager = parentFragmentManager
        )
    }
}