package com.example.taskmanager.domain.auth

data class TribeUser(
    val name: String,
    val firebaseId: String,
    val email: String,
    val timezone: String
)
