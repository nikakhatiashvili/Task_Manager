package com.example.taskmanager.presentation.profile.bot

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.InviteItemBinding
import com.example.taskmanager.domain.tribe.Invites

class InviteAdapter(private val onJoinClicked: (Long) -> Unit) :
    RecyclerView.Adapter<InviteAdapter.ViewHolder>() {

    var data: List<Invites> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: InviteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentData: Invites
        fun bind() {
            val context = itemView.context
            currentData = data[adapterPosition]
            binding.txtTribeName.text = currentData.tribeName
            binding.txtTribeDescription.text = currentData.tribeDescription
            binding.email.text = context.getString(R.string.invited_by, currentData.invitedBy)
            binding.btnJoin.setOnClickListener {
                onJoinClicked(currentData.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteAdapter.ViewHolder {
        return ViewHolder(
            InviteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InviteAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = data.size

}
