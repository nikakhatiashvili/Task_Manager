@file:SuppressWarnings("TooManyFunctions")

package com.example.taskmanager.presentation.main

import com.example.taskmanager.R
import com.example.taskmanager.Route
import com.example.taskmanager.StartDestinationAndRouteProvider
import com.example.taskmanager.presentation.signin.SignInMainRouter
import com.example.taskmanager.presentation.signup.SignUpMainRouter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRouterImpl @Inject constructor() : StartDestinationAndRouteProvider,
    StartDestinationMainRouter, SignInMainRouter, SignUpMainRouter {

    private val _startDestination = MutableStateFlow<Int?>(null)
    override val startDestination: Flow<Int> = _startDestination.asStateFlow().filterNotNull()

    private val _route = MutableStateFlow<Route?>(null)
    override val route: Flow<Route> = _route.asStateFlow().filterNotNull()

    override fun setSignInStartDestination() {
        _startDestination.value = R.id.signInFragment
    }

    override fun setTabsAsStartDestination() {
        _startDestination.value = R.id.tabsFragment
    }

    override fun onStartDestinationSet() {
        _startDestination.value = null
    }

    override fun onRouteExecuted() {
        _route.value = null
    }

    override fun goToTabs() {
        _route.value = { navController ->
            navController.navigate(R.id.action_signInFragment_to_tabsFragment)
        }
    }

    override fun goToSignUp() {
        _route.value = { navController ->
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun goSignUpToTabs() {
        _route.value = { navController ->
            navController.navigate(R.id.action_signUpFragment_to_tabsFragment)
        }
    }

    override fun goToSignIn() {
        _route.value = { navController ->
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }
}
