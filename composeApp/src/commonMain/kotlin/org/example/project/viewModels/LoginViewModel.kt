package org.example.project.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.example.project.data.AuthDataSource
import org.example.project.navigation.NavigationController
import org.example.project.navigation.NavigationController.NavigationEvent
import org.example.project.navigation.popUpToInclusive
import org.example.project.navigation.routes.AppRoute.Home
import org.example.project.navigation.routes.AppRoute.Login
import org.example.project.platform.DispatchersProvider

class LoginViewModel(
    private val authDataSource: AuthDataSource,
    dispatchersProvider: DispatchersProvider,
    private val navigationController: NavigationController,
) : ViewModel() {
    private var loginJob: Job? = null
    private val ioScope = CoroutineScope(dispatchersProvider.io())

    fun login(username: String, password: String) {
        loginJob = ioScope.launch {
            val loginResult = authDataSource.login(username = username, password = password)
            if (loginResult) {
                navigationController.login()
            }
        }
    }

    private suspend fun NavigationController.login() {
        sendNavigationEvent(
            NavigationEvent.Navigate(
                destinationRoute = Home,
                launchSingleTop = true,
                popUpTo = popUpToInclusive(startRoute = Login),
            )
        )
    }

    override fun onCleared() {
        super.onCleared()

        loginJob?.cancel()
    }
}