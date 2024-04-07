package com.msa.onlineshopzar.ui.navigation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    object BACK : Route(route = "back")


    object SplashScreen : Route(route = "splashScreen")


    //Login
    object LoginScreen : Route(route = "loginScreen")
    object NewPasswordScreen : Route(route = "newPasswordScreen")
    object NationalCodeResetPassScreen : Route(route = "nationalCodeResetPassScreen")
    object OtpScreen : Route(route = "otpScreen")

    //Home Product
    object HomeScreen : Route(route = "homeScreen")
    object DetailsProductScreen : Route(route = "detailsProductScreen")

    //Basket
    object ShoppingListScreen : Route(route = "shopppingListScreen")
    object InvoicePreviewScreen : Route(route = "invoicePreviewScreen")
    object OrderAddressRegistrationScreen : Route(route = "orderAddressRegistrationScreen")

    //Profile Customer
    object ProfileCustomerScreen : Route(route = "profileCustomerScreen")
    object ProfileAddressesScreen : Route(route = "profileAddressesScreen")
    object ProfileRestPasswordScreen : Route(route = "profileRestPasswordScreen")
    object AccountInformationScreen : Route(route = "accountInformationScreen")

}

