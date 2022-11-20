package com.test.boobluk.screens.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.ktx.Firebase
import com.test.boobluk.app.App
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.utils.navigation.goToAddNewFragment
import javax.inject.Inject
import kotlin.properties.Delegates

class EditProfileFragment : Fragment() {
    private val binding by lazy { FragmentEditProfileBinding.inflate(layoutInflater) }
    @Inject
    lateinit var editProfileViewModelFactory: EditProfileViewModelFactory
    private val editProfileViewModel: EditProfileViewModel by activityViewModels { editProfileViewModelFactory }
    private var launcher by Delegates.notNull<ActivityResultLauncher<Intent>>()
    private val firebase = Firebase

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
        initLauncher()
        doOnTextsChanges()
        bottomNavigationViewClickListener()
        buttonAddNewChatClickListener()
        saveChangesClickListenerAndSaveChanges()
        onClickChooseImageListener()
    }

    private fun initLauncher() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                binding.ivUserAvatar.setImageURI(result.data?.data)
            }
        }
    }

    private fun initConfig() {
        binding.mainBottomNavigationView.background = null
        editProfileViewModel.getCurrentUserFieldsForFragmentEditProfile(
            firebase = firebase,
            binding = binding,
            context = requireContext()
        )
    }

    private fun saveChangesClickListenerAndSaveChanges() {
        editProfileViewModel.toolbarClickListenerAndSaveChanges(
            firebase = firebase,
            binding = binding,
            fragment = this
        )
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun doOnTextsChanges() {
        editProfileViewModel.doOnTextChanges(binding = binding)
    }

    private fun buttonAddNewChatClickListener() {
        binding.itemAddChatNavigation.setOnClickListener {
            goToAddNewFragment()
        }
    }

    private fun bottomNavigationViewClickListener() {
        editProfileViewModel.bottomNavigationViewClickListener(
            binding = binding,
            parentFragmentManager = parentFragmentManager
        )
    }

    private fun onClickChooseImageListener() {
        editProfileViewModel.onClickChooseImageListener(
            binding = binding,
            launcher = launcher
        )
    }

}