package com.example.taskmanager

import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

interface RouteProvider {

    val route: Flow<Route>

    fun onRouteExecuted()
}

interface StartDestinationAndRouteProvider : RouteProvider {

    val startDestination: Flow<Int>

    fun onStartDestinationSet()
}

fun NavController.navigate(route: Route) {
    route(this)
}

typealias Route = (NavController) -> Unit
