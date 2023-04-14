package com.example.taskmanager.presentation.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.taskmanager.domain.group.MessagesItem

class MessagesItemDiffUtilCallBack : DiffUtil.ItemCallback<MessagesItem>() {

    override fun areItemsTheSame(oldItem: MessagesItem, newItem: MessagesItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessagesItem, newItem: MessagesItem): Boolean {
        return oldItem == newItem
    }
}