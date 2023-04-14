package com.example.taskmanager.presentation.groups

import android.view.View
import com.example.taskmanager.presentation.common.gone
import com.example.taskmanager.presentation.common.visible
import com.example.taskmanager.domain.tribe.UserGroupItem

interface GroupsUi {
    fun apply(
        adapter: GroupsAdapter,
        progressBar: View
    )

    object Empty : GroupsUi {
        override fun apply(adapter: GroupsAdapter, progressBar: View) =
            Unit
    }

    class Loading : GroupsUi {
        override fun apply(adapter: GroupsAdapter, progressBar: View) {
            progressBar.visible()
        }
    }

    class SuccessUi(private val list: List<UserGroupItem>) : GroupsUi {
        override fun apply(adapter: GroupsAdapter, progressBar: View) {
            adapter.data = list
            progressBar.gone()
        }
    }

    class ErrorUi : GroupsUi {
        override fun apply(adapter: GroupsAdapter, progressBar: View) {
            progressBar.gone()
        }
    }
}
