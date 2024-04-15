package com.msa.onlineshopzar.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import com.msa.onlineshopzar.data.remote.repository.LoginRepository
import com.msa.onlineshopzar.ui.navigation.navgraph.NavInfo
import com.msa.onlineshopzar.ui.navigation.navgraph.NavManager
import com.msa.onlineshopzar.ui.navigation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navManager: NavManager,
):ViewModel(){


    fun navigateToAccount() {
        navManager.navigate(
            NavInfo(id = Route.AccountInformationScreen.route,
                navOption = NavOptions.Builder().setPopUpTo(
                    Route.ProfileCustomerScreen.route,
                    inclusive = true).build())
        )
    }
}