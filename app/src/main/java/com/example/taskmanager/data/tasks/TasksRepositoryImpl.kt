package com.example.taskmanager.data.tasks

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tasks.TasksRepository
import com.example.taskmanager.domain.tasks.model.Habits
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val taskService: TaskService,
) : TasksRepository {
    override suspend fun updateTask(id: Long, completed: Boolean): Result<OkResponse> {
        val test = Date()
        val format = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault())
        val formattedDate = format.format(test)

        return taskService.updateTask(firebaseAuth.currentUser!!.uid, id, completed, formattedDate)
    }

    override suspend fun getTasks(): Result<Habits> {
        val test = Date()
        val format = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault())
        val formattedDate = format.format(test)
        return taskService.getTasks(firebaseAuth.currentUser!!.uid, formattedDate)
    }
}
