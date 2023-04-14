package com.example.taskmanager.data.group

import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.group.GroupRepository
import com.example.taskmanager.domain.group.Messages
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupService: GroupService,
    private val firebaseAuth: FirebaseAuth
) : GroupRepository {

    override suspend fun getGroupMessages(groupId: Int, page: Int): Result<Messages> {
        return groupService.getGroupMessages(firebaseAuth.currentUser!!.uid, groupId, page)
    }

}
