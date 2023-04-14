package com.example.taskmanager.domain.group

data class MessagesItem(
    val date: String,
    val groupId: Int,
    val id: Int,
    val message: String,
    val strDate: String,
    val taskId: Int,
    val userId: Int,
    val username: String
)
