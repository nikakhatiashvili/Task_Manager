package com.example.taskmanager.domain.tasks

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.domain.tasks.model.Habits
import com.example.taskmanager.common.Result

interface TasksRepository {

    suspend fun updateTask(id: Long, completed: Boolean): Result<OkResponse>

    suspend fun getTasks(): Result<Habits>
}
