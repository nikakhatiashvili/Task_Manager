package com.example.taskmanager.data.auth

import com.example.taskmanager.R
import com.example.taskmanager.common.ResourceManager
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.auth.AuthDataStore
import com.example.taskmanager.domain.auth.AuthRepository
import com.example.taskmanager.domain.auth.TribeUser
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authService: AuthService,
    private val resourceManager: ResourceManager,
    private val authDataStore: AuthDataStore,
) : AuthRepository {

    override suspend fun signUp(
        email: String,
        password: String,
        repeatPassword: String,
        name: String
    ): Result<AuthResult> {
        return try {
            if (password == repeatPassword) {
                val data = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                val user = data.user
                signUpSave(email, user!!.uid, name)
                Result.ApiSuccess(data)
            } else {
                Result.ApiError(null, resourceManager.provide(R.string.passwords_do_not_match))
            }
        } catch (e: FirebaseAuthException) {
            Result.ApiError(null, e.message.toString())
        }
    }

    private suspend fun signUpSave(email: String, uid: String, name: String) =
        authService.signUpSave(
            TribeUser(
                email = email,
                firebaseId = uid,
                name = name,
                timezone = TimeZone.getDefault().id
            )
        )

    override suspend fun signIn(email: String, password: String): Result<AuthResult> {
        return try {
            val data = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            saveUid(data.user?.uid.toString())
            Result.ApiSuccess(data)
        } catch (e: FirebaseAuthException) {
            Result.ApiError(null, e.message.toString())
        }
    }

    private suspend fun saveUid(uid: String) = authDataStore.saveUid(uid)

}
