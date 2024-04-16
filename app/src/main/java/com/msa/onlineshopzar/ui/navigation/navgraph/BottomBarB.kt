package com.msa.onlineshopzar.ui.navigation.navgraph

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.ui.navigation.bottomNav.NavigationBarItems

@Composable
fun BottomBarb(
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {

    val navigationBarItemsList = listOf(
        NavigationBarItems(route = "homeScreen", icon = ImageVector.vectorResource(R.drawable.iconproduct), title = "Product"),
        NavigationBarItems(route = "homeScreen", icon = ImageVector.vectorResource(R.drawable.report), title = "Report"),
        NavigationBarItems(route = "shopppingListScreen", icon = ImageVector.vectorResource(R.drawable.basket), title = "Basket"),
        NavigationBarItems(route = "profileCustomerScreen", icon = ImageVector.vectorResource(R.drawable.profile), title = "Profile"),
        // ادامه دادن برای سایر آیتم‌ها
    )
    val currentSection = navigationBarItemsList.first { it.route == currentRoute }
    Box(
        Modifier.navigationBarsPadding()
    ) {
        BottomNavigation(backgroundColor = Color.White, elevation = 0.dp) {
            navigationBarItemsList.forEach { section ->
                val selected = section == currentSection
                BottomNavigationItem(
                    label = {
                        Text(text =  section.title)
                    },
                    icon = {
                        Icon(
                            imageVector =  section.icon ,
                            contentDescription =  section.title
                        )
                    },
                    selected = selected,
                    unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
                    selectedContentColor = MaterialTheme.colors.onBackground,
                    onClick = { navigateToRoute(section.route) }
                )
            }
        }
    }
}


data class NavigationBarItems(
    val route: String,
    val icon: ImageVector,
    val title: String
)