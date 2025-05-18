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

class UserSettingsViewModel(
    private val authDataSource: AuthDataSource,
    dispatchersProvider: DispatchersProvider,
    private val navigationController: NavigationController,
) : ViewModel() {
    private var logoutJob: Job? = null
    private val ioScope = CoroutineScope(dispatchersProvider.io())

    fun logout() {
        logoutJob = ioScope.launch {
            val logoutResult = authDataSource.logout()
            if (logoutResult) {
                navigationController.logout()
            } else {
                // For simplicity, we are not notifying the user if there was an error with the logout and still
                // navigate to the Login screen
                navigationController.logout()
            }
        }
    }

    private suspend fun NavigationController.logout() {
        sendNavigationEvent(
            NavigationEvent.Navigate(
                destinationRoute = Login,
                launchSingleTop = true,
                popUpTo = popUpToInclusive(startRoute = Home)
            )
        )
    }

    override fun onCleared() {
        super.onCleared()

        logoutJob?.cancel()
    }
}