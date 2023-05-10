package com.example.taskmanager.presentation.detail

import android.view.View
import com.example.taskmanager.presentation.common.gone
import com.example.taskmanager.presentation.common.visible

interface GroupUiState {

    fun apply(

        progressBar: View
    )

    object Empty : GroupUiState {
        override fun apply(progressBar: View) =
            Unit
    }

    class Loading : GroupUiState {
        override fun apply(progressBar: View) {
            progressBar.visible()
        }
    }

    class SuccessUi : GroupUiState {
        override fun apply(progressBar: View) {
            progressBar.gone()
        }
    }

    class ErrorUi : GroupUiState {
        override fun apply(progressBar: View) {
            progressBar.gone()
        }
    }

}
