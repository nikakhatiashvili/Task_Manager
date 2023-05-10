package com.example.taskmanager.data.tasks

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tasks.model.Habits
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskService {

    @GET("task/get_tasks")
    suspend fun getTasks(
        @Query("firebaseId") firebaseId: String,
        @Query("date") date: String
    ): Result<Habits>

    @POST("task/complete_task")
    suspend fun updateTask(
        @Query("firebaseId") firebaseId: String,
        @Query("taskId") id: Long,
        @Query("complete") complete: Boolean,
        @Query("date") date: String
    ): Result<OkResponse>
}
