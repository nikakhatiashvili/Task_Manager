package com.example.taskmanager.presentation.profile.bot

import android.view.View
import com.example.taskmanager.presentation.common.visible
import com.example.taskmanager.domain.tribe.Invites
import com.example.taskmanager.presentation.common.gone

interface InviteUi {

    fun apply(adapter: InviteAdapter, progressBar: View, textView: View)

    object Empty : InviteUi {
        override fun apply(adapter: InviteAdapter, progressBar: View, textView: View) = Unit
    }

    class Loading : InviteUi {
        override fun apply(adapter: InviteAdapter, progressBar: View, textView: View) {
            progressBar.visible()
            textView.gone()
        }
    }

    class SuccessUi(private val list: List<Invites>) : InviteUi {
        override fun apply(adapter: InviteAdapter, progressBar: View, textView: View) {
            if (list.isEmpty()) textView.visible()
            else textView.gone()
            adapter.data = list
            progressBar.gone()
        }
    }

    class ErrorUi : InviteUi {
        override fun apply(adapter: InviteAdapter, progressBar: View, textView: View) {
            progressBar.gone()
            textView.gone()
        }
    }
}
