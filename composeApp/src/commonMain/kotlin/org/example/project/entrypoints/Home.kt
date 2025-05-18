package org.example.project.entrypoints

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.example.project.routes.HomeRoute.UserSettings
import org.example.project.screens.UserSettingsScreen
import org.example.project.viewModels.UserSettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.home(navHostController: NavHostController) {
    composable<UserSettings> {
        val userSettingsViewModel: UserSettingsViewModel = koinViewModel()
        UserSettingsScreen()
    }
}