package org.example.project.navigation.routes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.navigation.routes.base.Route

@Serializable
sealed interface HomeRoute : Route {

    @Serializable
    @SerialName("home--user-settings") // The double -- is to differentiate from the base and the nested roots
    data object UserSettings : HomeRoute
}