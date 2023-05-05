package com.example.taskmanager.presentation.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentAddTaskBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.gone
import com.example.taskmanager.presentation.common.viewBinding
import com.example.taskmanager.presentation.common.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment(R.layout.fragment_add_task) {

    private val binding by viewBinding(FragmentAddTaskBinding::bind)

    private val viewModel: AddTaskViewModel by viewModels()

    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }

    private fun init() {
        val habits = resources.getStringArray(R.array.habits)
        habits.random()
    }

    private fun setupCollects() {
        viewModel.getUsers()
        val habits = resources.getStringArray(R.array.habits)
        adapter = UsersAdapter { id, boolean ->
            viewModel.handleUsers(boolean, id)
        }
        with(binding) {

        }
        collectFlow(viewModel.users) {
            adapter.data = it
        }
    }

    private fun setupClickListeners() = with(binding) {
        viewModel.getUsers()
        val habits = resources.getStringArray(R.array.habits)
        adapter = UsersAdapter { id, boolean ->
            viewModel.handleUsers(boolean, id)
        }
        chosenUsersRV.gone()
        chosenUsersRV.adapter = adapter
        chosenUsersRV.layoutManager = LinearLayoutManager(requireContext())
        habit.editText?.hint = habits.random()
        chosenUsersRV.adapter = adapter
        chosenUsersRV.layoutManager = LinearLayoutManager(requireContext())
        forEveryone.setOnCheckedChangeListener { view, boolean ->
            if (!boolean) chosenUsersRV.visible() else chosenUsersRV.gone()
        }

        submitTask.setOnClickListener {
            viewModel.addTask(
                habit.editText?.text.toString(),
                habitDescriptionET.editText!!.text.toString(),
                forEveryone.isChecked
            )
        }
    }
}
