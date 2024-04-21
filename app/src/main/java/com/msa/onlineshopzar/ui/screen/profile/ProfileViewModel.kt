package com.msa.onlineshopzar.ui.screen.profile

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.msa.onlineshopzar.data.remote.repository.LoginRepository
import com.msa.onlineshopzar.ui.navigation.navgraph.NavInfo
import com.msa.onlineshopzar.ui.navigation.navgraph.NavManager
import com.msa.onlineshopzar.ui.navigation.navgraph.Route
import com.msa.onlineshopzar.utils.CompanionValues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navManager: NavManager,
) : ViewModel() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    fun navigateToAccount() {
        navManager.navigate(
            NavInfo(
                id = Route.AccountInformationScreen.route,
                navOption = NavOptions.Builder().setPopUpTo(
                    Route.ProfileCustomerScreen.route,
                    inclusive = true
                ).build()
            )
        )
    }


    fun navigateToLogin() {
        viewModelScope.launch {
            // حذف توکن قبلی
            sharedPreferences.edit {
                remove(CompanionValues.TOKEN)
            }
            navManager.navigate(
                NavInfo(
                    id = Route.LoginScreen.route,
                    navOption = NavOptions.Builder().setPopUpTo(
                        Route.ProfileCustomerScreen.route,
                        inclusive = true
                    ).build()
                )
            )
        }
    }
}