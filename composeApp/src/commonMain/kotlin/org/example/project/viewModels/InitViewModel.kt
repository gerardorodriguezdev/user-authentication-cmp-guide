package org.example.project.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.example.project.data.AuthDataSource
import org.example.project.navigation.NavigationController
import org.example.project.navigation.NavigationController.NavigationEvent.Navigate
import org.example.project.navigation.popUpToInclusive
import org.example.project.navigation.routes.AppRoute.*
import org.example.project.platform.DispatchersProvider

class InitViewModel(
    private val authDataSource: AuthDataSource,
    dispatchersProvider: DispatchersProvider,
    private val navigationController: NavigationController,
) : ViewModel() {
    private var isUserLoggedInJob: Job? = null
    private val ioScope = CoroutineScope(dispatchersProvider.io())

    init {
        isUserLoggedInJob = ioScope.launch {
            val isUserLoggedIn = authDataSource.isUserLoggedIn()
            if (isUserLoggedIn) {
                navigateHome()
            } else {
                navigateLogin()
            }
        }
    }

    private suspend fun navigateHome() {
        navigationController.sendNavigationEvent(
            Navigate(
                destinationRoute = Home,
                launchSingleTop = true,
                popUpTo = popUpToInclusive(startRoute = Init)
            )
        )
    }

    private suspend fun navigateLogin() {
        navigationController.sendNavigationEvent(
            Navigate(
                destinationRoute = Login,
                launchSingleTop = true,
                popUpTo = popUpToInclusive(startRoute = Init)
            )
        )
    }

    override fun onCleared() {
        super.onCleared()

        ioScope.cancel()
    }
}