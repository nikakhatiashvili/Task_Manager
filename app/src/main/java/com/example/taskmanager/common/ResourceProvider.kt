package com.example.taskmanager.common

import androidx.annotation.StringRes

interface ResourceProvider {

    fun string(@StringRes id: Int): String
}
