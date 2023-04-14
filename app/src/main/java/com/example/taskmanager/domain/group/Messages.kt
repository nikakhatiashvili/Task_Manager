package com.example.taskmanager.domain.group

data class Messages(
    val content: List<MessagesItem>,
    val empty: Boolean?,
    val first: Boolean?,
    val last: Boolean?,
    val number: Int?,
    val numberOfElements: Int?,
    val pageable: Pageable?,
    val size: Int?,
)
