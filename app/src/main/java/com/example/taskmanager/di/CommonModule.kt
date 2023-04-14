package com.example.taskmanager.di

import android.content.Context
import com.example.taskmanager.R
import com.example.taskmanager.common.Dispatchers
import com.example.taskmanager.common.ResourceManager
import com.example.taskmanager.data.auth.AuthService
import com.example.taskmanager.data.group.GroupService
import com.example.taskmanager.data.tasks.TaskService
import com.example.taskmanager.data.tasks.TasksRepositoryImpl
import com.example.taskmanager.data.tribe.TribeService
import com.example.taskmanager.domain.tasks.TasksRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideResourceManager(@ApplicationContext context: Context): ResourceManager =
        ResourceManager.Base(context)

    @Provides
    fun provideTasksRepository(taskService: TaskService): TasksRepository {
        return TasksRepositoryImpl(provideFirebaseAuth(), taskService)
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideGroupService(retrofit: Retrofit): GroupService =
        retrofit.create(GroupService::class.java)

    @Provides
    @Singleton
    fun provideTaskService(retrofit: Retrofit): TaskService =
        retrofit.create(TaskService::class.java)

    @Provides
    @Singleton
    fun provideTribeService(retrofit: Retrofit): TribeService =
        retrofit.create(TribeService::class.java)

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()
}
