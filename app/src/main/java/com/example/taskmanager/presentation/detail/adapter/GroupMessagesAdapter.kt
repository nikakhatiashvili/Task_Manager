package com.example.taskmanager.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.taskmanager.databinding.MessageItemBinding
import com.example.taskmanager.domain.group.MessagesItem


class GroupMessagesAdapter(private val onPersonIdClicked: (Int) -> Unit) :
    PagingDataAdapter<MessagesItem, MessagesItemViewHolder>(MessagesItemDiffUtilCallBack()) {

    companion object {
        private const val MESSAGE_ITEM = 1
        private const val NOT_SUPPORTED_ITEM = "Item type is not supported"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesItemViewHolder {
        val view = MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessagesItemViewHolder(view) { position ->
            val personId = getItem(position)!!.userId
            onPersonIdClicked(personId)
        }
    }

    override fun onBindViewHolder(holder: MessagesItemViewHolder, position: Int) =
        holder.bind(getItem(position)!!)

    override fun getItemViewType(position: Int): Int = MESSAGE_ITEM
}

