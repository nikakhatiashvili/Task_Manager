package com.example.taskmanager.domain.manageTribe

interface TribeDataStore {
    suspend fun getTribeId(): String
    suspend fun saveTribeId(tribe: String)
    suspend fun removeTribeId()
    suspend fun hasTribeId(): Boolean
}
