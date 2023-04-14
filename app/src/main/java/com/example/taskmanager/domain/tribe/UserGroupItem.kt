package com.example.taskmanager.domain.tribe

data class UserGroupItem(
    val adminId: String,
    val id: Int,
    val tasks: List<Long>?,
    val tribeDescription: String,
    val tribeName: String
)
