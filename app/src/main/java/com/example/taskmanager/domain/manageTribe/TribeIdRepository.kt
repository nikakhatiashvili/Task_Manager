package com.example.taskmanager.domain.manageTribe

import com.example.taskmanager.common.Result
import kotlinx.coroutines.flow.Flow

interface TribeIdRepository {

    suspend fun saveTribeId()
}
