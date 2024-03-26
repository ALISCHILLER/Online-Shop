@file:OptIn(ExperimentalMaterial3Api::class)

package com.msa.onlineshopzar.ui.navigation.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.msa.onlineshopzar.ui.navigation.bottomNav.BottomNavaghtion
import com.msa.onlineshopzar.ui.screen.home.HomeScreen
import com.msa.onlineshopzar.ui.screen.login.LoginScreen
import com.msa.onlineshopzar.ui.screen.splash.SplashScreen
import com.msa.onlineshopzar.ui.theme.OnlineShopZarTheme

@ExperimentalMaterial3Api
@Composable
fun SetupNavigator() {

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(
                    navController.currentBackStackEntryAsState()
                        .value?.destination?.route
                )
            ) {
                BottomNavaghtion()
            }

        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
//            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.LoginScreen.route) {
                LoginScreen()
            }

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
    return currentRoute != Route.SplashScreen.route && currentRoute != Route.LoginScreen.route
}


@Preview
@Composable
private fun SetupNavigatorPreview() {
    OnlineShopZarTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SetupNavigator()
        }
    }
}