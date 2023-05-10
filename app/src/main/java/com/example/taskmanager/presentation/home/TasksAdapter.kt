package com.example.taskmanager.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.GroupItemBinding
import com.example.taskmanager.databinding.TaskBinding
import com.example.taskmanager.domain.tasks.model.CompleteTask

class TasksAdapter(private val onCompleteChanged: (CompleteTask) -> Unit) :
    ListAdapter<TasksAdapter.ListItem, RecyclerView.ViewHolder>(DiffCallback()) {

    companion object {
        const val GROUP_VIEW_TYPE = 0
        const val TASK_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GROUP_VIEW_TYPE -> {
                val view =
                    GroupItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GroupViewHolder(view)
            }

            TASK_VIEW_TYPE -> {
                val view = TaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TaskViewHolder(view, onCompleteChanged)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder.itemViewType) {
            GROUP_VIEW_TYPE -> (holder as GroupViewHolder).bind(item as ListItem.GroupItem)
            TASK_VIEW_TYPE -> (holder as TaskViewHolder).bind(item as ListItem.TaskItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListItem.GroupItem -> GROUP_VIEW_TYPE
            is ListItem.TaskItem -> TASK_VIEW_TYPE
            else -> throw IllegalArgumentException("Invalid data type")
        }
    }

    class GroupViewHolder(private val binding: GroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(groupItem: ListItem.GroupItem) {
            binding.root.text = groupItem.groupName
        }
    }

    class TaskViewHolder(
        private val binding: TaskBinding,
        private val onCompleteChanged: (CompleteTask) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskItem: ListItem.TaskItem) {
            binding.txtTaskName.text = taskItem.task.name
            binding.txtTaskDescription.text = taskItem.task.description
            binding.taskSwtich.isChecked = taskItem.completed
            //catch value change on switch and update the taskItem.completed
            binding.taskSwtich.setOnCheckedChangeListener { _, isChecked ->
                onCompleteChanged(CompleteTask(taskItem.task.id, isChecked))
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.GroupItem && newItem is
                        ListItem.GroupItem -> oldItem.id == newItem.id

                oldItem is ListItem.TaskItem && newItem is
                        ListItem.TaskItem -> oldItem.task.id == newItem.task.id

                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }

    sealed class ListItem(val id: Int) {
        data class GroupItem(val groupName: String) : ListItem(groupName.hashCode())
        data class TaskItem(
            val task: com.example.taskmanager.domain.tasks.TaskX,
            var completed: Boolean
        ) : ListItem(task.id)
    }
}
