package com.example.taskmanager.domain.tasks

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tasks.model.Habits

interface TasksRepository {

    suspend fun updateTask(id: Long, completed: Boolean): Result<OkResponse>

    suspend fun getTasks(): Result<Habits>
}
