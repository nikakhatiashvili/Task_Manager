package com.example.taskmanager.presentation.profile.bot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.R
import com.example.taskmanager.databinding.InviteBottomSheetBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.viewBinding
import com.example.taskmanager.presentation.profile.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InviteBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private val binding by viewBinding(InviteBottomSheetBinding::bind)

    private lateinit var adapter: InviteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.invite_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getInvites()
        setUpListeners()
    }

    private fun setUpListeners() {
        adapter = InviteAdapter(viewModel::joinTribe)

        binding.inviteRecyclerview.adapter = adapter
        binding.inviteRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        collectFlow(viewModel.inviteState) { ui ->
            ui.apply(adapter, binding.loadingProgressBar, binding.invites)
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
