package org.example.project.di

import org.example.project.data.AuthDataSource
import org.example.project.data.FakeAuthDataSource
import org.example.project.viewModels.InitViewModel
import org.example.project.viewModels.LoginViewModel
import org.example.project.viewModels.UserSettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single { FakeAuthDataSource() } bind AuthDataSource::class
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