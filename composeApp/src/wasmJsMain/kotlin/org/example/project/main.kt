package org.example.project

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToNavigation
import androidx.navigation.compose.rememberNavController
import kotlinx.browser.document
import kotlinx.browser.window
import org.example.project.entrypoints.App

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val navHostController = rememberNavController()

        App()

        LaunchedEffect(Unit) {
            // This allows us to update the url shown in the user's browser
            window.bindToNavigation(navHostController)
        }
    }
}