package com.example.taskmanager.presentation.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: TasksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollects()
    }

    private fun setupCollects() {
        viewModel.getTasks()
        adapter = TasksAdapter {
            if (it.completed) {
                viewModel.updateTask(it.id, it.completed)
            } else {
                showConfirmationDialog(it.id)
            }
        }
        binding.tasksRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.tasksRecyclerview.adapter = adapter
        collectFlow(viewModel.taskState) { ui ->
            ui.apply(adapter, binding.loadingProgressBar)
        }
    }

    private fun showConfirmationDialog(id: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Are you sure you want to mark this task as uncompleted?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.updateTask(id, false)
            }
            .setNegativeButton("No") { _, _ ->
                // change switch to true again
            }
        val dialog = builder.create()
        dialog.show()
    }

}
