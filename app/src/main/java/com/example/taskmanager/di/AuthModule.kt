package com.example.taskmanager.di

import com.example.taskmanager.data.auth.AuthRepositoryImpl
import com.example.taskmanager.data.group.GroupRepositoryImpl
import com.example.taskmanager.data.manageTribe.ManageTribeRepositoryImpl
import com.example.taskmanager.data.tribe.TribeRepositoryImpl
import com.example.taskmanager.domain.auth.AuthRepository
import com.example.taskmanager.domain.group.GroupRepository
import com.example.taskmanager.domain.manageTribe.ManageTribeRepository
import com.example.taskmanager.domain.tribe.TribeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindsTribeRepository(tribeRepositoryImpl: TribeRepositoryImpl): TribeRepository

    @Binds
    fun bindsGroupRepository(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository

    @Binds
    fun bindsManageTribeRepository(manageTribeRepositoryImpl: ManageTribeRepositoryImpl): ManageTribeRepository
}
