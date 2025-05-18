package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.project.navigation.NavigationController.NavigationEvent
import org.example.project.navigation.NavigationController.NavigationEvent.Navigate
import org.example.project.navigation.NavigationController.NavigationEvent.PopBackStack

/**
 * Observer that converts NavigationEvents from the [NavigationController] into actual navigation using the NavHost
 */
interface NavigationControllerObserver {
    val navigationController: NavigationController
    val navHostController: NavHostController

    suspend fun subscribe() {
        coroutineScope {
            launch {
                navigationController.navigationEvents.collect(::onNavigationEvent)
            }
        }
    }

    fun onNavigationEvent(navigationEvent: NavigationEvent)
}

class DefaultNavigationControllerObserver(
    override val navigationController: NavigationController,
    override val navHostController: NavHostController,
) : NavigationControllerObserver {

    override fun onNavigationEvent(navigationEvent: NavigationEvent) {
        when (navigationEvent) {
            is Navigate -> {
                navHostController.navigate(route = navigationEvent.destinationRoute) {
                    navigationEvent.popUpTo?.let { popUpTo ->
                        popUpTo(popUpTo.startRoute) {
                            inclusive = popUpTo.isInclusive
                            saveState = popUpTo.saveState
                        }
                    }
                }
            }

            is PopBackStack -> navHostController.popBackStack()
        }
    }
}

@Composable
fun rememberNavigationObserver(
    navigationController: NavigationController,
    navHostController: NavHostController,
): NavigationControllerObserver =
    remember {
        DefaultNavigationControllerObserver(navigationController, navHostController)
    }