package com.example.taskmanager.data.auth

import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.auth.TribeUser
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("user/signup")
    suspend fun signUpSave(@Body tribeUser: TribeUser): Result<String>
}
