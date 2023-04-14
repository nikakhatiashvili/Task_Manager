package com.example.taskmanager.presentation.tribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.R
import com.example.taskmanager.common.Result
import com.example.taskmanager.common.ResourceManager
import com.example.taskmanager.domain.manageTribe.ManageTribeRepository
import com.example.taskmanager.domain.tasks.model.DomainTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TribeViewModel @Inject constructor(
    private val manageTribeRepository: ManageTribeRepository,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _tribeSuccess = Channel<String>()
    val tribeSuccess = _tribeSuccess.receiveAsFlow()

    private val _tribeError = Channel<String>()
    val tribeError = _tribeError.receiveAsFlow()

    private val _taskError = Channel<String>()
    val taskError = _taskError.receiveAsFlow()

    fun inviteUser(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = manageTribeRepository.inviteUser(email)) {
                is Result.ApiSuccess -> _tribeSuccess.send("successfully invited $email")
                is Result.ApiException -> _tribeError.send(result.e.message.toString())
                is Result.ApiError -> _tribeError.send(result.message.toString())
            }
        }
    }

    fun addTask(task: DomainTask) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = manageTribeRepository.addTask(task)) {
                is Result.ApiSuccess -> _tribeSuccess.send("successfully added task")
                is Result.ApiException -> _taskError.send(resourceManager.provide(R.string.task_error))
                is Result.ApiError -> _taskError.send(resourceManager.provide(R.string.task_error))
            }
        }
    }
}
