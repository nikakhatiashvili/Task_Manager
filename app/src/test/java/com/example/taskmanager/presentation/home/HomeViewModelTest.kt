package com.example.taskmanager.presentation.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.MainDispatcherRule
import com.example.taskmanager.TestDispachers
import com.example.taskmanager.common.Dispatchers
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tasks.TasksRepository
import com.example.taskmanager.domain.tasks.model.Habits
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: TasksRepository
    @Mock
    private lateinit var dispatchers: Dispatchers

//    @Rule
//    @JvmField
//    val testCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel:HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        dispatchers = TestDispachers(mainDispatcherRule.testDispatcher)

        val tasksResponse = Habits(emptyList())
        val result = Result.ApiSuccess(tasksResponse)
        runTest {
            Mockito.`when`(repository.getTasks()).thenReturn(result)
        }

        viewModel = HomeViewModel(repository,dispatchers )
    }



    @Test
    fun `getTasks should set taskState to SuccessUi when tasks are retrieved successfully`() = runTest {
        assert(viewModel.taskState.value is HomeUi.SuccessUi)
    }

    fun getTasks() {
    }


    fun updateTask() {

    }
}