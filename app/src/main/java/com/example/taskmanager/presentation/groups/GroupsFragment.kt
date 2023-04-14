package com.example.taskmanager.presentation.groups

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.databinding.GroupsFragmentBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupsFragment : Fragment(com.example.taskmanager.R.layout.groups_fragment) {

    private val binding by viewBinding(GroupsFragmentBinding::bind)

    private val viewModel: GroupsViewModel by viewModels()

    private lateinit var adapter: GroupsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGroups()
        setupClickListeners()
        setupCollects()
    }

    private fun setupCollects() {
        adapter = GroupsAdapter({
            val tribeBottomSheet = TribeBottomSheet.newInstance(it.toLong())
            tribeBottomSheet.show(parentFragmentManager, TribeBottomSheet.TAG)
        }, { tribeId, tribeName ->
            viewModel.goToTribe(tribeId, tribeName)


        })
        binding.groupsRecyclerView.adapter = adapter
        binding.groupsRecyclerView.layoutManager = LinearLayoutManager(context)
        collectFlow(viewModel.groupsState) {
            it.apply(
                adapter = adapter,
                progressBar = binding.loadingProgressBar
            )
        }
    }

    private fun setupClickListeners() = with(binding) {
    }

}
