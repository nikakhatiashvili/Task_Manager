package com.example.taskmanager.presentation.tabs.di

import com.example.taskmanager.Route
import com.example.taskmanager.RouteProvider
import com.example.taskmanager.presentation.groups.GroupsFragmentDirections
import com.example.taskmanager.presentation.groups.router.GroupTabsRouter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@TabsScope
class TabsRouterImpl @Inject constructor() : RouteProvider, GroupTabsRouter {

    private val _route = MutableStateFlow<Route?>(null)
    override val route: Flow<Route> = _route.asStateFlow().filterNotNull()

    override fun onRouteExecuted() {
        _route.value = null
    }

    override fun goToGroup(id: Int, name: String) {
        _route.value = { navController ->
            val action = GroupsFragmentDirections.actionGroupsFragmentToDetailFragment2(id, name)
            navController.navigate(action)
        }
    }

//
//    override fun goToAccountDetails() {
//        _route.value = { navController ->
//            navController.navigate(R.id.action_accountsFragment_to_accountDetailsFragment)
//        }
//    }
}
