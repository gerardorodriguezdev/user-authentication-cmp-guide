package org.example.project.entrypoints

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.example.project.navigation.routes.HomeRoute.UserSettings
import org.example.project.screens.UserSettingsScreen
import org.example.project.viewModels.UserSettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.home() {
    composable<UserSettings> {
        val userSettingsViewModel = koinViewModel<UserSettingsViewModel>()
        UserSettingsScreen(
            onLogout = { userSettingsViewModel.logout() },
        )
    }
}