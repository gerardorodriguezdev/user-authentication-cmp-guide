package org.example.project.routes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.routes.base.Route

@Serializable
sealed interface HomeRoute : Route {

    @Serializable
    @SerialName("home--user-settings")
    data object UserSettings : HomeRoute
}