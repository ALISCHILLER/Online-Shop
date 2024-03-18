package com.msa.onlineshopzar.ui.navigation.navgraph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.msa.onlineshopzar.ui.screen.home.HomeScreen
import com.msa.onlineshopzar.ui.screen.splash.SplashScreen

@ExperimentalMaterial3Api
@Composable
fun DeiceNavigator(

) {

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(
                    navController.currentBackStackEntryAsState()
                        .value?.destination?.route
                )
            ) {

            }

        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.SplashScreen.route) {
                SplashScreen()
            }

            composable(route = Route.HomeScreen.route) {
                HomeScreen()
            }

        }
    }
}


// تابع برای تعیین اینکه آیا bottomBar باید نمایش داده شود یا خیر
fun shouldShowBottomBar(currentRoute: String?): Boolean {
    return currentRoute != Route.SplashScreen.route
}