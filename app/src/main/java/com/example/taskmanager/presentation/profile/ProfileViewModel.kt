package com.example.taskmanager.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.common.Dispatchers
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.auth.AuthDataStore
import com.example.taskmanager.domain.manageTribe.TribeDataStore
import com.example.taskmanager.domain.tribe.TribeRepository
import com.example.taskmanager.presentation.profile.bot.InviteUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val tribeRepository: TribeRepository,
    private val authDataStore: AuthDataStore,
    private val tribeDataStore: TribeDataStore,
    private val dispatchers: Dispatchers
) : ViewModel() {


    private val _tribeError = Channel<String>()
    val tribeError = _tribeError.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _inviteState = MutableStateFlow<InviteUi>(InviteUi.Empty)
    val inviteState = _inviteState.asStateFlow()

    fun createTribe(name: String, description: String) {
        dispatchers.launchBackground(viewModelScope) {
            _isLoading.value = true
            when (val data = tribeRepository.createTribe(name, description)) {
                is Result.ApiSuccess -> println("succesfully created tribe")
                is Result.ApiException -> _tribeError.send("test")
                is Result.ApiError -> _tribeError.send("data.message.toString()")
            }
            _isLoading.value = false
        }
    }

    fun getInvites() {
        dispatchers.launchBackground(viewModelScope) {
            _inviteState.emit(InviteUi.Loading())
            when (val result = tribeRepository.getInvites()) {
                is Result.ApiSuccess -> _inviteState.emit(InviteUi.SuccessUi(result.data))
                is Result.ApiError -> _inviteState.emit(InviteUi.ErrorUi())
                is Result.ApiException -> _inviteState.emit(InviteUi.ErrorUi())
            }
        }
    }

    fun joinTribe(id: Long) {
        dispatchers.launchBackground(viewModelScope) {
            when (val result = tribeRepository.acceptInvite(id)) {
                is Result.ApiSuccess -> _tribeError.send(result.data.message)
                is Result.ApiException -> _tribeError.send(result.e.message.toString())
                is Result.ApiError -> _tribeError.send(result.message.toString())
            }
        }
    }

    fun logOut() {
        dispatchers.launchBackground(viewModelScope) {
            authDataStore.removeUid()
            tribeDataStore.removeTribeId()
        }
    }
}
