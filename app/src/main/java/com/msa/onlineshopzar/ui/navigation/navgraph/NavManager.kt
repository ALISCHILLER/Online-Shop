

package com.msa.onlineshopzar.ui.navigation.navgraph

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavManager @Inject constructor() {

    private val _routeInfo = MutableStateFlow(NavInfo())
    val routeInfo: StateFlow<NavInfo> = _routeInfo

    fun navigate(routeInfo: NavInfo?) {
        //Clear previous error, when navigating

        _routeInfo.update { routeInfo ?: NavInfo() }
    }
}