package com.example.taskmanager.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentDetailBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.onClick
import com.example.taskmanager.presentation.common.viewBinding
import com.example.taskmanager.presentation.detail.adapter.GroupMessagesAdapter
import com.example.taskmanager.presentation.detail.adapter.RepositoryLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val viewModel: GroupViewModel by viewModels()

    private lateinit var groupMessagesAdapter: GroupMessagesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestGroup()
        setupClickListeners()
        setupCollects()
    }

    private fun setupCollects() {
        val groupName = args.groupName
        binding.tvName.text = groupName

        groupMessagesAdapter = GroupMessagesAdapter({ int -> })
        binding.messageRecyclerview.adapter = groupMessagesAdapter.withLoadStateFooter(
            footer = RepositoryLoadStateAdapter { groupMessagesAdapter.retry() }
        )
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.reverseLayout = true
        binding.messageRecyclerview.layoutManager = layoutManager
        collectFlow(viewModel.messages) {
            groupMessagesAdapter.submitData(it)
        }

        collectFlow(viewModel.groupUiState) {
            it.apply(binding.loadingProgressBar)
        }
    }

    private fun setupClickListeners() = with(binding) {
        btnClose.onClick { requireActivity().onBackPressed() }
    }
}
