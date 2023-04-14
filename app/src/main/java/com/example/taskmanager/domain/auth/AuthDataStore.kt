package com.example.taskmanager.domain.auth

interface AuthDataStore {

    suspend fun saveUid(token: String)

    suspend fun hasUid(): Boolean

    suspend fun removeUid()

    suspend fun getUid(): String
}
