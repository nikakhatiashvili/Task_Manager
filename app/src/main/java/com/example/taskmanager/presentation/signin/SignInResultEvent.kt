package com.example.taskmanager.presentation.signin

import android.content.Context
import com.example.taskmanager.R
import com.example.taskmanager.presentation.common.showMessage

interface SignInResultEvent {

    fun apply(context: Context?)

    class Success(private val signInMainRouter: SignInMainRouter) :
        SignInResultEvent {
        override fun apply(context: Context?) {
            context?.showMessage(context.resources.getString(R.string.sign_in_welcome))
            signInMainRouter.goToTabs()
        }
    }

    class Error(private val errorMessage: String) : SignInResultEvent {
        override fun apply(context: Context?) {
            context?.showMessage(errorMessage)
        }
    }
}
