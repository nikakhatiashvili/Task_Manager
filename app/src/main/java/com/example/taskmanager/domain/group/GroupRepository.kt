package com.example.taskmanager.domain.group

import com.example.taskmanager.common.Result

interface GroupRepository {

    suspend fun getGroupMessages(groupId: Int, page: Int): Result<Messages>

}
