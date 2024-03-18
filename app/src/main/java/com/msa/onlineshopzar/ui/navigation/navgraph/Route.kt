package com.msa.onlineshopzar.ui.navigation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object SplashScreen : Route(route = "splashScreen")
    object HomeScreen : Route(route = "homeScreen")
}