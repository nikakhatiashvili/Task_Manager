package com.example.taskmanager.presentation.tribe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTribeBinding
import com.example.taskmanager.domain.tasks.model.DomainTask
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.toast
import com.example.taskmanager.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TribeFragment : Fragment(R.layout.fragment_tribe) {
    private val binding by viewBinding(FragmentTribeBinding::bind)

    private val viewModel: TribeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }

    private fun setupCollects() {
        collectFlow(viewModel.tribeError) {
            context?.toast(it)
        }
        collectFlow(viewModel.taskError) {
            context?.toast(it)
        }
        collectFlow(viewModel.tribeSuccess) {
            context?.toast(it)
        }
        setFragmentResultListener("createTask") { _, result ->
            val name = result.getString("name")
            val description = result.getString("description")
            viewModel.addTask(DomainTask(name = name, description = description))
        }
    }

    private fun setupClickListeners() = with(binding) {
        create.setOnClickListener {
            val dialog = TribeDialogFragment()
            dialog.show(parentFragmentManager, requireContext().getString(R.string.dialog))
        }
        btnInvite.setOnClickListener {
            val email = etEmail.editText?.text.toString().trim()
            viewModel.inviteUser(email)
        }
    }
}
