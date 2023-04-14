package com.example.taskmanager.domain.tasks

data class TasksByGroup(
    val groupName: String,
    val tasks: List<TaskX>
)
