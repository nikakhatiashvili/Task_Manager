package com.example.taskmanager.data

import android.content.Context
import com.example.taskmanager.common.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {

    override fun string(id: Int): String = context.getString(id)
}
