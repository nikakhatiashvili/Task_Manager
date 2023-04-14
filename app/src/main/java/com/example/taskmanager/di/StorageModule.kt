package com.example.taskmanager.di

import com.example.taskmanager.data.auth.AuthDataStoreImpl
import com.example.taskmanager.domain.auth.AuthDataStore
import com.example.taskmanager.domain.manageTribe.TribeDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StorageModule {

    @Binds
    fun bindsAuthDataStore(impl: AuthDataStoreImpl): AuthDataStore

    @Binds
    fun bindsTribeDataStore(impl: AuthDataStoreImpl): TribeDataStore

}
