package com.example.taskmanager.data.auth

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.taskmanager.domain.auth.AuthDataStore
import com.example.taskmanager.domain.manageTribe.TribeDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthDataStoreImpl @Inject constructor(@ApplicationContext private val context: Context) :
    AuthDataStore, TribeDataStore {

    companion object {
        private const val TOKEN = "token"
        private const val TRIBE_ID = "tribe"
        private const val AUTH_DATA_STORE = "AUTH_DATA_STORE"
        private const val EMPTY_STRING = ""

        private val Context.dataStore by preferencesDataStore(name = AUTH_DATA_STORE)
    }

    override suspend fun saveUid(token: String) {
        val dataStoreKey = stringPreferencesKey(TOKEN)
        context.dataStore.edit { it[dataStoreKey] = token }
    }

    override suspend fun hasUid(): Boolean {
        return getUid().isNotEmpty()
    }

    override suspend fun getUid(): String {
        val dataStoreKey = stringPreferencesKey(TOKEN)
        val preference = context.dataStore.data.first()
        return preference[dataStoreKey] ?: EMPTY_STRING
    }

    override suspend fun removeUid() {
        val dataStoreKey = stringPreferencesKey(TOKEN)
        context.dataStore.edit { token -> token[dataStoreKey] = EMPTY_STRING }
    }

    override suspend fun getTribeId(): String {
        val tribeId = stringPreferencesKey(TRIBE_ID)
        val preference = context.dataStore.data.first()
        return preference[tribeId] ?: EMPTY_STRING
    }

    override suspend fun saveTribeId(tribe: String) {
        val tribeKey = stringPreferencesKey(TRIBE_ID)
        context.dataStore.edit { it[tribeKey] = tribe }
    }

    override suspend fun removeTribeId() {
        val tribeKey = stringPreferencesKey(TRIBE_ID)
        context.dataStore.edit { token -> token[tribeKey] = EMPTY_STRING }
    }

    override suspend fun hasTribeId(): Boolean {
        println("asdasdasd".plus(getTribeId().isNotEmpty()))
        return getTribeId().isNotEmpty()
    }
}
