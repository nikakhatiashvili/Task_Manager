package com.example.taskmanager.presentation.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.common.Dispatchers
import com.example.taskmanager.common.OkResponse
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.manageTribe.ManageTribeRepository
import com.example.taskmanager.domain.tribe.TribeRepository
import com.example.taskmanager.presentation.tabs.di.TabsComponentHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val manageTribeRepository: ManageTribeRepository,
    private val tribeRepository: TribeRepository,
    private val tabsComponentHolder: TabsComponentHolder,
) : ViewModel() {

    private val _groupsState = MutableStateFlow<GroupsUi>(GroupsUi.Empty)
    val groupsState = _groupsState.asStateFlow()

    private val _inviteState = MutableStateFlow<Result<OkResponse>?>(null)
    val inviteState = _inviteState.asStateFlow()

    private val _leaveGroupState = MutableStateFlow<Result<OkResponse>?>(null)
    val leaveGroupState = _leaveGroupState.asStateFlow()


    fun inviteUser(email: String) {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            _inviteState.emit(manageTribeRepository.inviteUser(email))
        }

    }

    fun getGroups() {
        dispatchers.launchBackground(viewModelScope) {
            when (val res = tribeRepository.getGroups()) {
                is Result.ApiSuccess -> _groupsState.emit(GroupsUi.SuccessUi(res.data))
                is Result.ApiError -> _groupsState.emit(GroupsUi.ErrorUi())
                is Result.ApiException -> _groupsState.emit(GroupsUi.ErrorUi())
            }
        }
    }

    fun leaveGroup(groupId: Long) {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            _leaveGroupState.emit(manageTribeRepository.leaveGroup(groupId))
        }
    }

    fun goToTribe(it: Int, tribeName: String) {
        tabsComponentHolder.getGroupsProvider()?.goToGroup(it, tribeName)
    }

}
