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
import org.example.project.navigation.RegisterNavigationControllerObserver
import org.example.project.navigation.routes.AppRoute.*
import org.example.project.navigation.routes.HomeRoute.UserSettings
import org.example.project.screens.InitScreen
import org.example.project.screens.LoginScreen
import org.example.project.viewModels.InitViewModel
import org.example.project.viewModels.LoginViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
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
            RegisterNavigationControllerObserver(
                navigationController = koinInject(),
                navHostController = navHostController
            )

            NavHost(
                navController = navHostController,
                startDestination = Init,
            ) {
                composable<Init> {
                    val initViewModel = koinViewModel<InitViewModel>()
                    InitScreen()
                }

                composable<Login> {
                    val loginViewModel = koinViewModel<LoginViewModel>()
                    LoginScreen(
                        onLogin = { username: String, password: String ->
                            loginViewModel.login(username, password)
                        }
                    )
                }

                navigation<Home>(startDestination = UserSettings) {
                    home()
                }
            }
        }
    }
}