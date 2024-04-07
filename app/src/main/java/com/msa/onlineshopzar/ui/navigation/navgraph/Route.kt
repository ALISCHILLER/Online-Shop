package com.msa.onlineshopzar.ui.navigation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object SplashScreen : Route(route = "splashScreen")

    //Login
    object LoginScreen : Route(route = "loginScreen")
    object NewPasswordScreen : Route(route = "newPasswordScreen")

    //Home Product
    object HomeScreen : Route(route = "homeScreen")
    object DetailsProductScreen : Route(route = "detailsProductScreen")

    //Basket
    object ShopppingListScreen : Route(route = "shopppingListScreen")
    object InvoicePreviewScreen : Route(route = "invoicePreviewScreen")
    object OrderAddressRegistrationScreen : Route(route = "orderAddressRegistrationScreen")

    //Profile Customer
    object ProfileCustomerScreen : Route(route = "profileCustomerScreen")
    object ProfileAddressesScreen : Route(route = "profileAddressesScreen")
    object ProfileRestPasswordScreen : Route(route = "profileRestPasswordScreen")
    object AccountInformationScreen : Route(route = "accountInformationScreen")





}