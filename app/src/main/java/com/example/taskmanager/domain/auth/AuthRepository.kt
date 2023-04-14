package com.example.taskmanager.domain.auth

import com.example.taskmanager.common.Result
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun signUp(
        email: String,
        password: String,
        repeatPassword: String,
        name: String
    ): Result<AuthResult>

    suspend fun signIn(email: String, password: String): Result<AuthResult>
}
