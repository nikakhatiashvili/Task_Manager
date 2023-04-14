package com.example.taskmanager.domain.tasks.model

import com.example.taskmanager.domain.tasks.TasksByGroup

data class Habits(
    val tasksByGroup: List<TasksByGroup>
)
