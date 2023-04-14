package com.example.taskmanager.di

import com.example.taskmanager.StartDestinationAndRouteProvider
import com.example.taskmanager.presentation.main.MainRouterImpl
import com.example.taskmanager.presentation.main.StartDestinationMainRouter
import com.example.taskmanager.presentation.signin.SignInMainRouter
import com.example.taskmanager.presentation.signup.SignUpMainRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface MainRouterModule {

    @Binds
    fun bindSignUpDestinationRouter(impl: MainRouterImpl): SignUpMainRouter

    @Binds
    fun bindSignInDestinationRouter(impl: MainRouterImpl): SignInMainRouter

    @Binds
    fun bindStartDestinationMainRouter(impl: MainRouterImpl): StartDestinationMainRouter

    @Binds
    fun bindStartDestinationAndRouteProvider(impl: MainRouterImpl): StartDestinationAndRouteProvider

}
