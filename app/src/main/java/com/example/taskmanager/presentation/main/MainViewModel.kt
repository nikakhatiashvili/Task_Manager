package com.example.taskmanager.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.domain.auth.AuthDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val startDestinationMainRouter: StartDestinationMainRouter,
    private val authDataStore: AuthDataStore
) : ViewModel() {

    fun onAppLaunched() {
        viewModelScope.launch {
            if (authDataStore.hasUid()) {
                startDestinationMainRouter.setTabsAsStartDestination()
            } else {
                startDestinationMainRouter.setSignInStartDestination()
            }
        }
    }

}
