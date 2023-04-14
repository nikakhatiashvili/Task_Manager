package com.example.taskmanager.domain.manageTribe

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tasks.model.DomainTask

interface ManageTribeRepository {

    suspend fun inviteUser(email: String): Result<OkResponse>

    suspend fun addTask(task: DomainTask): Result<OkResponse>

    suspend fun leaveGroup(groupId: Long): Result<OkResponse>
}
