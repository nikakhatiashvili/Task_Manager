package com.example.taskmanager.domain.tasks

data class TaskX(
    val assignedTo: List<Any>?,
    val comment: Any?,
    val completedTodayBy: List<Any>?,
    val description: String,
    val email: Any?,
    val forAll: Boolean?,
    val groupId: Int,
    val id: Int,
    var completed: Boolean,
    val name: String,
    val resetTime: Any?
)
