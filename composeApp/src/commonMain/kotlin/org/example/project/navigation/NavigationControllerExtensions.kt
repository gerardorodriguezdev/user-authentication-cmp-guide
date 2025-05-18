package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import org.example.project.navigation.NavigationController.NavigationEvent
import org.example.project.navigation.routes.base.Route

fun popUpToInclusive(
    startRoute: Route,
    saveState: Boolean = false,
): NavigationEvent.Navigate.PopUpTo =
    NavigationEvent.Navigate.PopUpTo(
        startRoute = startRoute,
        isInclusive = true,
        saveState = saveState,
    )

/**
 * Convenience composable that connects the NavHost and the [NavigationController] by a [NavigationControllerObserver]
 */
@Composable
fun RegisterNavigationControllerObserver(
    navigationController: NavigationController,
    navHostController: NavHostController
) {
    val navigationControllerObserver: NavigationControllerObserver =
        rememberNavigationObserver(navigationController, navHostController)

    LaunchedEffect(navHostController, navigationController, navigationControllerObserver) {
        navigationControllerObserver.subscribe()
    }
}