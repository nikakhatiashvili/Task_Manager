package com.example.taskmanager.presentation.home

import com.example.taskmanager.MainDispatcherRule
import com.example.taskmanager.TestDispachers
import com.example.taskmanager.common.Dispatchers
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.tasks.TasksRepository
import com.example.taskmanager.domain.tasks.model.Habits
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: TasksRepository

    @Mock
    private lateinit var dispatchers: Dispatchers

    private lateinit var viewModel:HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        dispatchers = TestDispachers(mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `getTasks should set taskState to SuccessUi when tasks are retrieved successfully`() = runTest {
        val tasksResponse = Habits(emptyList())
        val result = Result.ApiSuccess(tasksResponse)
        Mockito.`when`(repository.getTasks()).thenReturn(result)
        viewModel = HomeViewModel(repository,dispatchers)
        assert(viewModel.taskState.value is HomeUi.SuccessUi)
    }

    @Test
    fun `getTasks should set taskState to ErrorUi after api call fails`() = runTest {
        val result = Result.ApiError<Habits>(1,"")
        Mockito.`when`(repository.getTasks()).thenReturn(result)
        viewModel = HomeViewModel(repository,dispatchers)
        assert(viewModel.taskState.value is HomeUi.ErrorUi)
    }

    @Test
    fun `getTasks should set taskState to LoadingUi before tasks are retrieved`() = runTest {
        val tasksResponse = Habits(emptyList())
        val result = Result.ApiSuccess(tasksResponse)
        Mockito.`when`(repository.getTasks()).then {
            runBlocking {
                this.cancel("waiting")
            }.wait()
            return@then result
        }
        viewModel = HomeViewModel(repository,dispatchers)
        assert(viewModel.taskState.value is HomeUi.Loading)
    }

}
