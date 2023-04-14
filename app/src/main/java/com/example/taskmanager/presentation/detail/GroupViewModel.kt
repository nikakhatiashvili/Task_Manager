package com.example.taskmanager.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.taskmanager.domain.group.GroupRepository
import com.example.taskmanager.common.Dispatchers
import com.example.taskmanager.domain.group.MessagesItem
import com.example.taskmanager.presentation.detail.adapter.GroupMessageDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

private const val PERSON_ID_KEY = "groupId"

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val groupRepository: GroupRepository,
    private val dispatchers: com.example.taskmanager.common.Dispatchers
) : ViewModel() {

    private val _groupUiState = MutableStateFlow<GroupUiState>(GroupUiState.Empty)
    val groupUiState = _groupUiState.asStateFlow()

    private val _messages =
        MutableStateFlow<PagingData<MessagesItem>>(PagingData.empty())
    val messages get() = _messages.asStateFlow()

    fun requestGroup() {
        val groupId = state.get<Int>(PERSON_ID_KEY)!!
        getGroup(groupId)
    }

    private fun getGroup(groupId: Int) {

        dispatchers.launchBackground(viewModelScope) {
            _groupUiState.emit(GroupUiState.Loading())
            Pager(config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
            ),
                pagingSourceFactory = {
                    GroupMessageDataSource(
                        groupRepository, groupId
                    )
                }
            ).flow.cachedIn(viewModelScope).collectLatest {
                _messages.value = it
                _groupUiState.emit(GroupUiState.SuccessUi())
            }
        }
    }

}
