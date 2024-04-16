@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.msa.onlineshopzar.ui.navigation.navgraph

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.msa.onlineshopzar.MainActivity
import com.msa.onlineshopzar.ui.navigation.bottomNav.BottomNavaghtion
import com.msa.onlineshopzar.ui.screen.accountInformation.AccountInformationScreen
import com.msa.onlineshopzar.ui.screen.basket.ShoppingListScreen
import com.msa.onlineshopzar.ui.screen.detailsProduct.DetailsProductScreen
import com.msa.onlineshopzar.ui.screen.home.HomeScreen
import com.msa.onlineshopzar.ui.screen.invoicepreview.InvoicePreviewScreen
import com.msa.onlineshopzar.ui.screen.login.LoginScreen
import com.msa.onlineshopzar.ui.screen.orderAddressRegistration.OrderAddressRegistrationScreen
import com.msa.onlineshopzar.ui.screen.profile.ProfileCustomerScreen
import com.msa.onlineshopzar.ui.screen.profileAddresses.ProfileAddressesScreen
import com.msa.onlineshopzar.ui.screen.profileRestPassword.ProfileRestPasswordScreen
import com.msa.onlineshopzar.ui.screen.resetPassword.NationalCodeResetPassScreen
import com.msa.onlineshopzar.ui.screen.resetPassword.OtpScreen
import com.msa.onlineshopzar.ui.screen.splash.SplashScreen
import com.msa.onlineshopzar.ui.theme.OnlineShopZarTheme
import timber.log.Timber

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MainActivity.SetupNavigator() {

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value


    val navInfo by navManager.routeInfo.collectAsState()
    LaunchedEffect(key1 = navInfo) {
        navInfo.id?.let {
            if (it == Route.BACK.route) {
                // firebaseAnalytics.logEvent("Click_Back",null)

                navController.navigateUp()
                navManager.navigate(null)
                return@let
            }
            var bundle= Bundle()
            bundle.putString("link",it)
            // firebaseAnalytics.logEvent("Click_Navigate",bundle)

            navController.navigate(it, navOptions = navInfo.navOption)
            navManager.navigate(null)
        }

    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(
                    navController.currentBackStackEntryAsState()
                        .value?.destination?.route
                )
            ) {
                BottomNavaghtion(onClick = {
                    navigateToTab(
                        navController = navController,
                        route =it
                    )
                })
//                navController.currentBackStackEntryAsState().value?.destination?.route?.let {
//                    BottomBarb(
//                        currentRoute= it,
//                        navigateToRoute={
//                            navigateToTab(
//                                navController = navController,
//                                route =it
//                            )
//                        }
//                    )
//                }
            }

        }
    ) {
        Timber.tag("SetupNavigator").e("SetupNavigator SetupNavigator: ")
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.LoginScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.SplashScreen.route) { SplashScreen() }

            //Login
            composable(route = Route.LoginScreen.route) { LoginScreen() }
            composable(route = Route.NationalCodeResetPassScreen.route) { NationalCodeResetPassScreen() }
            composable(route = Route.OtpScreen.route) { OtpScreen() }

            //product
            composable(route = Route.HomeScreen.route) { HomeScreen() }
            composable(route = Route.DetailsProductScreen.route) { DetailsProductScreen() }

            //basket
            composable(route = Route.ShoppingListScreen.route) { ShoppingListScreen(navController) }
            composable(route = Route.InvoicePreviewScreen.route) { InvoicePreviewScreen() }
            composable(route = Route.OrderAddressRegistrationScreen.route) { OrderAddressRegistrationScreen() }

            //profile
            composable(route = Route.ProfileCustomerScreen.route) { ProfileCustomerScreen() }
            composable(route = Route.ProfileAddressesScreen.route) { ProfileAddressesScreen() }
            composable(route = Route.ProfileRestPasswordScreen.route) { ProfileRestPasswordScreen() }
            composable(route = Route.AccountInformationScreen.route) { AccountInformationScreen() }


        }
    }
}


// تابع برای تعیین اینکه آیا bottomBar باید نمایش داده شود یا خیر
fun shouldShowBottomBar(currentRoute: String?): Boolean {
    return currentRoute != Route.SplashScreen.route && currentRoute != Route.LoginScreen.route
}


@ExperimentalFoundationApi
@Preview
@Composable
private fun SetupNavigatorPreview() {
    OnlineShopZarTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}