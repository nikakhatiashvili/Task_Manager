package com.example.taskmanager

import com.example.taskmanager.common.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.withContext

class TestDispachers(val dispacher: TestDispatcher) : Dispatchers {
        override fun launchUI(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) = scope.launch(dispacher.scheduler, block = block)

        override fun launchBackground(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) = scope.launch(dispacher.scheduler, block = block)

        override fun launchDefault(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) = scope.launch(dispacher.scheduler, block = block)

        override suspend fun changeToUi(block: suspend CoroutineScope.() -> Unit) {
            withContext(dispacher.scheduler, block)
        }
    }
