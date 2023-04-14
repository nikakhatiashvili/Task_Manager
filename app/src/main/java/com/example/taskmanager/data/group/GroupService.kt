package com.example.taskmanager.data.group

import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.group.Messages
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupService {

    @GET("task/messages")
    suspend fun getGroupMessages(
        @Query("firebaseId") firebaseId: String,
        @Query("groupId") groupId: Int,
        @Query("pageNumber") pageNumber: Int
    ): Result<Messages>

}
