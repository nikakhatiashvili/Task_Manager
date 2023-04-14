package com.example.taskmanager.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.toast
import com.example.taskmanager.presentation.common.viewBinding
import com.example.taskmanager.presentation.profile.bot.InviteBottomSheet
import com.example.taskmanager.presentation.tabs.TabsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : androidx.fragment.app.Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()

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

    private fun setupClickListeners() = with(binding) {
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
            findNavController().navigate(R.id.action_profileFragment_to_tribeFragment)
        }

        btnJoinTribe.setOnClickListener {
            val inviteBottomSheet = InviteBottomSheet()
            inviteBottomSheet.show(childFragmentManager, InviteBottomSheet.TAG)
        }
    }
}
