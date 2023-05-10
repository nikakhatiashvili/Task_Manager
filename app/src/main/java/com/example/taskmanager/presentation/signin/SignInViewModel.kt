package com.example.taskmanager.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.common.Dispatchers
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInMainRouter: SignInMainRouter,
    private val dispatchers: Dispatchers,
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private val _signInResultEvent = Channel<SignInResultEvent>()
    val signInResultEvent = _signInResultEvent.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun goToSignUp() {
        signInMainRouter.goToSignUp()
    }

    fun signIn(email: String, password: String) {
        dispatchers.launchBackground(viewModelScope) {
            _isLoading.value = true
            when (val result = signInUseCase.invoke(email, password)) {
                is Result.ApiSuccess -> {
                    _signInResultEvent.send(SignInResultEvent.Success(signInMainRouter))
                }

                is Result.ApiError -> _signInResultEvent.send(SignInResultEvent.Error(result.message.toString()))
                is Result.ApiException -> _signInResultEvent.send(SignInResultEvent.Error(result.e.message.toString()))
            }
            _isLoading.value = false
        }
    }
}
