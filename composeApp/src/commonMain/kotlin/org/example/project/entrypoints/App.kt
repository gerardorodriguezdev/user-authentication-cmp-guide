package org.example.project.entrypoints

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import org.example.project.di.appModule
import org.example.project.di.coreModule
import org.example.project.di.homeModule
import org.example.project.routes.AppRoute.*
import org.example.project.routes.HomeRoute.UserSettings
import org.example.project.screens.InitScreen
import org.example.project.screens.LoginScreen
import org.example.project.viewModels.InitViewModel
import org.example.project.viewModels.LoginViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(navHostController: NavHostController = rememberNavController()) {
    MaterialTheme {
        KoinApplication(
            application = {
                modules(
                    coreModule,
                    appModule,
                    homeModule,
                )
            }
        ) {
            NavHost(
                navController = navHostController,
                startDestination = Init,
            ) {
                composable<Init> {
                    val initViewModel: InitViewModel = koinViewModel()
                    InitScreen()
                }

                composable<Login> {
                    val loginViewModel: LoginViewModel = koinViewModel()
                    LoginScreen()
                }

                navigation<Home>(startDestination = UserSettings) {
                    home(navHostController)
                }
            }
        }
    }
}