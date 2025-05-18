package org.example.project.navigation.routes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.navigation.routes.base.Route

@Serializable
sealed interface AppRoute : Route {

    @Serializable
    @SerialName("init")
    data object Init : AppRoute

    @Serializable
    @SerialName("login")
    data object Login : AppRoute

    @Serializable
    @SerialName("home")
    data object Home : AppRoute
}
