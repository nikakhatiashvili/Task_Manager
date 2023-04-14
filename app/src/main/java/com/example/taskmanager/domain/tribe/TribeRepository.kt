package com.example.taskmanager.domain.tribe

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result

interface TribeRepository {

    suspend fun createTribe(name: String, description: String): Result<OkResponse>

    suspend fun acceptInvite(id: Long): Result<OkResponse>

    suspend fun getInvites(): Result<List<Invites>>

    suspend fun getGroups(): Result<List<UserGroupItem>>

}
