package com.example.taskmanager.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.LoadStateLayoutBinding

class RepositoryLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<RepositoryLoadStateAdapter.LoadStateViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            LoadStateLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: LoadStateLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryBtn.setOnClickListener {
                retry.invoke()
            }
        }

        fun onBind(loadState: LoadState) {
            binding.apply {
                when (loadState) {
                    is LoadState.Loading -> {
                        chooseState(true)
                    }

                    is LoadState.NotLoading -> {
                        chooseState(false)
                    }

                    is LoadState.Error -> {
                        chooseState(false, loadState.error.message)
                    }
                }
            }
        }

        private fun chooseState(isLoading: Boolean, message: String? = null) {
            binding.apply {
                loadingProgress.isVisible = isLoading
                retryBtn.isVisible = !isLoading
                errorTv.isVisible = !isLoading
                errorTv.text = message
            }
        }
    }
}
