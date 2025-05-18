package org.example.project.viewModels

import androidx.lifecycle.ViewModel
import org.example.project.data.AuthDataSource
import org.example.project.navigation.NavigationController

class LoginViewModel(
    private val authDataSource: AuthDataSource,
    private val navigationController: NavigationController,
) : ViewModel()