package com.example.taskmanager.data.manageTribe

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result
import com.example.taskmanager.data.tribe.TribeService
import com.example.taskmanager.domain.manageTribe.ManageTribeRepository
import com.example.taskmanager.domain.tasks.model.DomainTask
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ManageTribeRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val tribeService: TribeService,
) : ManageTribeRepository {

    override suspend fun inviteUser(email: String): Result<OkResponse> {
        return tribeService.inviteUser(firebaseAuth.currentUser!!.uid, email)
    }

    override suspend fun addTask(task: DomainTask): Result<OkResponse> {
        return tribeService.addTask(task, firebaseAuth.currentUser!!.uid)
    }

    override suspend fun leaveGroup(groupId: Long): Result<OkResponse> {
        return tribeService.leaveGroup(firebaseAuth.currentUser!!.uid, groupId)
    }
}
