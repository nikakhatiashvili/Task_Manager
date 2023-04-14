package com.example.taskmanager.domain.tasks.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainTask(
    val completed: Boolean? = false,
    val description: String? = "",
    val name: String? = ""
) : Parcelable
