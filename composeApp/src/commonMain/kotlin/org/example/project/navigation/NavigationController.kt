package org.example.project.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.example.project.navigation.NavigationController.NavigationEvent
import org.example.project.navigation.routes.base.Route

/**
 * Proxy to send and receive navigation events from the navController inside ViewModels
 */
interface NavigationController {
    val navigationEvents: SharedFlow<NavigationEvent>

    suspend fun sendNavigationEvent(navigationEvent: NavigationEvent)

    sealed interface NavigationEvent {
        data class Navigate(
            val destinationRoute: Route,
            val launchSingleTop: Boolean = false,
            val restoreState: Boolean = false,
            val popUpTo: PopUpTo? = null,
        ) : NavigationEvent {
            data class PopUpTo(
                val startRoute: Route,
                val isInclusive: Boolean,
                val saveState: Boolean,
            )
        }

        data object PopBackStack : NavigationEvent
    }
}

class DefaultNavigationController : NavigationController {
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    override val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents

    override suspend fun sendNavigationEvent(navigationEvent: NavigationEvent) {
        _navigationEvents.emit(navigationEvent)
    }
}
