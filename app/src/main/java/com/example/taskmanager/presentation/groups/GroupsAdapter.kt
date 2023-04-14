package com.example.taskmanager.presentation.groups

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.GroupsItemBinding
import com.example.taskmanager.domain.tribe.UserGroupItem

class GroupsAdapter(
    private val onSettingsClicked: (Int) -> Unit,
    private val onTribeClick: (Int, String) -> Unit
) :
    RecyclerView.Adapter<GroupsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsAdapter.ViewHolder {
        return ViewHolder(
            GroupsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onSettingsClicked, onTribeClick
        )
    }

    var data: List<UserGroupItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(
        private val binding: GroupsItemBinding,
        private val onSettingsClicked: (Int) -> Unit,
        private val onTribeClick: (Int, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivSettings.setOnClickListener { onSettingsClicked(data[absoluteAdapterPosition].id) }
            binding.root.setOnClickListener {
                onTribeClick(
                    data[absoluteAdapterPosition].id,
                    data[absoluteAdapterPosition].tribeName
                )
            }
        }

        private lateinit var currentData: UserGroupItem
        fun bind() {
            currentData = data[adapterPosition]
            binding.txtTribeName.text = currentData.tribeName
            binding.txtTribeDescription.text = currentData.tribeDescription

        }
    }

    override fun onBindViewHolder(holder: GroupsAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = data.size

}

