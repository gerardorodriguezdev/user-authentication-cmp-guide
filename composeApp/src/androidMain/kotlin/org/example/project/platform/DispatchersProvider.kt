package org.example.project.platform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun defaultDispatchersProvider(): DispatchersProvider =
    object : DispatchersProvider {
        override fun ui(): CoroutineDispatcher = Dispatchers.Main
        override fun io(): CoroutineDispatcher = Dispatchers.IO
    }