package com.example.taskmanager.presentation.home

import android.view.View
import com.example.taskmanager.domain.tasks.TasksByGroup
import com.example.taskmanager.presentation.common.gone
import com.example.taskmanager.presentation.common.visible

interface HomeUi {
    fun apply(adapter: TasksAdapter, progressBar: View)

    object Empty : HomeUi {
        override fun apply(adapter: TasksAdapter, progressBar: View) = Unit
    }

    class Loading : HomeUi {
        override fun apply(adapter: TasksAdapter, progressBar: View) {
            progressBar.visible()
        }
    }

    class SuccessUi(private val list: List<TasksByGroup>) : HomeUi {
        override fun apply(adapter: TasksAdapter, progressBar: View) {
            println(list)
            adapter.submitList(list.flatMap { group ->
                listOf(TasksAdapter.ListItem.GroupItem(group.groupName)) + group.tasks.map { task ->
                    TasksAdapter.ListItem.TaskItem(task, task.completed)
                }
            })
            progressBar.gone()
        }
    }

    class ErrorUi : HomeUi {
        override fun apply(adapter: TasksAdapter, progressBar: View) {
            progressBar.gone()
        }
    }
}
