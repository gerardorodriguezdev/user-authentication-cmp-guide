package org.example.project.di

import org.example.project.viewModels.InitViewModel
import org.example.project.viewModels.LoginViewModel
import org.example.project.viewModels.UserSettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::InitViewModel)
    viewModelOf(::LoginViewModel)
}

val homeModule = module {
    viewModelOf(::UserSettingsViewModel)
}