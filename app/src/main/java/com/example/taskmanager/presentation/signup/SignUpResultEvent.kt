package com.example.taskmanager.presentation.signup

import android.content.Context
import com.example.taskmanager.presentation.common.showMessage
import com.example.taskmanager.R

interface SignUpResultEvent {

    fun apply(context: Context?)

    class Success(private val signUpMainRouter: SignUpMainRouter, private val name: String) :
        SignUpResultEvent {
        override fun apply(context: Context?) {
            context?.showMessage(context.resources.getString(R.string.welcome, name))
            signUpMainRouter.goToSignIn()
        }
    }

    class Error(private val errorMessage: String) : SignUpResultEvent {
        override fun apply(context: Context?) {
            context?.showMessage(errorMessage)
        }
    }
}
