package com.example.taskmanager.presentation.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.MessageItemBinding
import com.example.taskmanager.domain.group.MessagesItem
import java.text.SimpleDateFormat
import java.util.Locale

class MessagesItemViewHolder(
    private val binding: MessageItemBinding,
    private val onPersonPositionClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onPersonPositionClicked(adapterPosition) }
    }

    fun bind(item: MessagesItem) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)
        val outputFormat = SimpleDateFormat("M/d/yyyy hh:mm a", Locale.US)

        val date = inputFormat.parse(item.date)
        val outputStr = outputFormat.format(date)
        with(binding) {
            nameTextview.text = item.username
            dateTextview.text = outputStr
            contentTextview.text = item.message
        }
    }
}