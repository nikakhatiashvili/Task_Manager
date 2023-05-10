package com.example.taskmanager.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tasks.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val dispatchers: com.example.taskmanager.common.Dispatchers
) : ViewModel() {

    private val _taskState = MutableStateFlow<HomeUi>(HomeUi.Empty)
    val taskState = _taskState.asStateFlow()

    init {
        getTasks()
    }

    fun getTasks() {
        dispatchers.launchBackground(viewModelScope) {
            _taskState.emit(HomeUi.Loading())
            when (val res = tasksRepository.getTasks()) {
                is Result.ApiSuccess -> _taskState.emit(HomeUi.SuccessUi(res.data.tasksByGroup))
                is Result.ApiError -> _taskState.emit(HomeUi.ErrorUi())
                is Result.ApiException -> _taskState.emit(HomeUi.ErrorUi())
            }
        }
    }

    fun updateTask(id: Int, completed: Boolean) {
        dispatchers.launchBackground(viewModelScope) {
            when (val res = tasksRepository.updateTask(id.toLong(), completed)) {
                is Result.ApiSuccess -> println("test " + res.data)
                is Result.ApiError -> _taskState.emit(HomeUi.ErrorUi())
                is Result.ApiException -> _taskState.emit(HomeUi.ErrorUi())
            }
        }
    }
}
