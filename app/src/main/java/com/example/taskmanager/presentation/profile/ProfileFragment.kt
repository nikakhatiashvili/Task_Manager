package com.example.taskmanager.presentation.profile

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.toast
import com.example.taskmanager.presentation.common.viewBinding
import com.example.taskmanager.presentation.profile.bot.InviteBottomSheet
import com.example.taskmanager.presentation.tabs.TabsFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : androidx.fragment.app.Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()

    companion object {
        private const val EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()

    }

    private fun setupCollects() {
        collectFlow(viewModel.tribeError) {
            context?.toast(it)
        }
        setFragmentResultListener("createTribe") { _, result ->
            val name = result.getString("name")
            val description = result.getString("description")
            viewModel.createTribe(name.toString(), description.toString())
        }
        collectFlow(viewModel.isLoading) { isLoading ->
            binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun requestStoragePermission() {
        storagePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private val storagePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            storageOpener.launch("image/*")
        } else {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                showPermissionNeedsToBeGrantedDialog()
            }
        }
    }


    private fun showPermissionNeedsToBeGrantedDialog() {
        MaterialAlertDialogBuilder(requireContext()).setTitle(
            getString(R.string.permission_required),
        )
            .setMessage(getString(R.string.permission_req_dialog))
            .setNegativeButton(getString(R.string.deny_from_dialog)) { dialog, _ ->
                dialog.dismiss()
            }.setPositiveButton(getString(R.string.accept_from_dialog)) { d, _ ->
                d.dismiss()
            }.show()
    }


    private val storageOpener = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { resultUri ->
        if (resultUri != null) {
            Glide.with(requireContext())
                .load(resultUri)
                .circleCrop()
                .into(binding.image)
        }
    }

    private fun chooseFromGallery() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            storageOpener.launch("image/*")
        } else {
            requestStoragePermission()
        }
    }

    private fun setupClickListeners() = with(binding) {
        image.setOnClickListener {
            chooseFromGallery()
        }

        btnCreateTribe.setOnClickListener {
            val dialog = DialogFragment()
            dialog.show(parentFragmentManager, requireContext().getString(R.string.dialog))
        }

        btnLogOut.setOnClickListener {
            viewModel.logOut()
            val navHostFragment = parentFragment as NavHostFragment?
            val d = navHostFragment!!.requireParentFragment() as TabsFragment
            d.logOut()
        }

        btnManageTribe.setOnClickListener {
        }

        btnJoinTribe.setOnClickListener {
            val inviteBottomSheet = InviteBottomSheet()
            inviteBottomSheet.show(childFragmentManager, InviteBottomSheet.TAG)
        }
    }
}
