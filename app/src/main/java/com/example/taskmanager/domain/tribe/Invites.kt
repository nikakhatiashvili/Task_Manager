package com.example.taskmanager.domain.tribe

data class Invites(
    val id: Long,
    val groupId: Int,
    val userFirebaseId: String,
    val tribeName: String,
    val tribeDescription: String,
    val invitedBy: String
)
