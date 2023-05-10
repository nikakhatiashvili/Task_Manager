package com.example.taskmanager.presentation.tabs.di

import com.example.taskmanager.RouteProvider
import com.example.taskmanager.presentation.groups.router.GroupTabsRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn

@InstallIn(TabsComponent::class)
@Module
interface TabsRouterModule {

    @Binds
    fun bindRouteProvider(impl: TabsRouterImpl): RouteProvider

    @Binds
    fun bindGroupRouteProvider(impl: TabsRouterImpl): GroupTabsRouter


//    @Binds
//    fun bindPositionsTabsRouter(impl: TabsRouterImpl): PositionsTabRouter
//
//    @Binds
//    fun bindProjectsTabsRouter(impl: TabsRouterImpl): ProjectsTabRouter
//
//    @Binds
//    fun bindAccountsTabsRouter(impl: TabsRouterImpl): AccountsTabRouter
}
