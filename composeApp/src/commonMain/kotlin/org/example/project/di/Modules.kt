package org.example.project.di

import org.example.project.data.AuthDataSource
import org.example.project.data.FakeAuthDataSource
import org.example.project.navigation.DefaultNavigationController
import org.example.project.navigation.NavigationController
import org.example.project.platform.DispatchersProvider
import org.example.project.platform.defaultDispatchersProvider
import org.example.project.viewModels.InitViewModel
import org.example.project.viewModels.LoginViewModel
import org.example.project.viewModels.UserSettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    singleOf(::DefaultNavigationController) bind NavigationController::class
    single { FakeAuthDataSource() } bind AuthDataSource::class
    single { defaultDispatchersProvider() } bind DispatchersProvider::class
}

val appModule = module {
    includes(coreModule)

    viewModelOf(::InitViewModel)
    viewModelOf(::LoginViewModel)
}

val homeModule = module {
    includes(coreModule)

    viewModelOf(::UserSettingsViewModel)
}