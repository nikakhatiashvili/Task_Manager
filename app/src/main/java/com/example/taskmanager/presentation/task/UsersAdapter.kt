package com.example.taskmanager.presentation.task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.UserItemBinding
import com.example.taskmanager.domain.tasks.UserUi

class UsersAdapter(private val onCheckboxClicked: (String, Boolean) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var data: List<UserUi> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentData: UserUi
        fun bind() {
            currentData = data[adapterPosition]
            binding.email.text = currentData.email
            binding.name.text = currentData.name
            binding.forEveryone.setOnCheckedChangeListener { view, isChecked ->
                onCheckboxClicked(currentData.id, isChecked)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = data.size

}
