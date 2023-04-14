package com.example.taskmanager.data.tribe

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tasks.model.DomainTask
import com.example.taskmanager.domain.tribe.Invites
import com.example.taskmanager.domain.tribe.TribeGroup
import com.example.taskmanager.domain.tribe.UserGroupItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TribeService {

    @POST("group/create")
    suspend fun createTribe(@Body tribeGroup: TribeGroup): Result<OkResponse>

    @POST("group/invite_user")
    suspend fun inviteUser(
        @Query("firebaseId") firebaseId: String,
        @Query("email") email: String
    ): Result<OkResponse>

    @GET("group/invites")
    suspend fun getInvites(@Query("firebaseId") firebaseId: String): Result<List<Invites>>

    @POST("group/invite")
    suspend fun acceptInvite(
        @Query("id") id: Long,
        @Query("accept") accept: Boolean,
        @Query("firebaseId") firebaseId: String,
    ): Result<OkResponse>

    @POST("task/create")
    suspend fun addTask(
        @Body task: DomainTask,
        @Query("firebaseId") firebaseId: String
    ): Result<OkResponse>

    @GET("group/groups")
    suspend fun getGroups(@Query("firebaseId") firebaseId: String): Result<List<UserGroupItem>>

    @POST("group/leave_group")
    suspend fun leaveGroup(
        @Query("firebaseId") firebaseId: String,
        @Query("groupId") groupId: Long
    ): Result<OkResponse>
}
