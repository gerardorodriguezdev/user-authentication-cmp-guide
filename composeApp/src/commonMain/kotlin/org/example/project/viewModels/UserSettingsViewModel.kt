package org.example.project.viewModels

import androidx.lifecycle.ViewModel
import org.example.project.data.AuthDataSource

class UserSettingsViewModel(
    private val authDataSource: AuthDataSource,
) : ViewModel()