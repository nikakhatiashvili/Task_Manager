@file:SuppressWarnings("WildcardImport")

package com.example.taskmanager.data.tribe

import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tribe.*
import com.example.taskmanager.domain.tribe.Invites
import com.example.taskmanager.domain.tribe.TribeGroup
import com.example.taskmanager.domain.tribe.UserGroupItem
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class TribeRepositoryImpl @Inject constructor(
    private val tribeService: TribeService,
    private val firebaseAuth: FirebaseAuth,
) : TribeRepository {

    override suspend fun createTribe(name: String, description: String): Result<OkResponse> =
        tribeService.createTribe(TribeGroup(name, description, firebaseAuth.currentUser?.uid!!))


    override suspend fun acceptInvite(id: Long): Result<OkResponse> {
        return tribeService.acceptInvite(id, true, firebaseAuth.currentUser!!.uid)
    }

    override suspend fun getInvites(): Result<List<Invites>> =
        tribeService.getInvites(firebaseAuth.currentUser!!.uid)

    override suspend fun getGroups(): Result<List<UserGroupItem>> {
        return tribeService.getGroups(firebaseAuth.currentUser!!.uid)
    }

}
