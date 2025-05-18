package org.example.project.platform

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
}

expect fun defaultDispatchersProvider() : DispatchersProvider