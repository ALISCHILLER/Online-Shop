package com.msa.onlineshopzar.ui.screen.detailsProduct

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.ui.theme.PlatinumSilver
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun DetailsProductScreen() {

    Scaffold(
        modifier = Modifier
            .background(color = PlatinumSilver),
        topBar = {
            TopBarDetails()
        }
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = Modifier
                    .background(color = PlatinumSilver)
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .aspectRatio(2f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.product),
                        contentDescription = "product",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                val scope = rememberCoroutineScope()
                val pagerState = rememberPagerState(pageCount = { HomeTabs.entries.size })
                val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .aspectRatio(1.3f)
                ) {
                    TabRow(
                        selectedTabIndex = selectedTabIndex.value,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        HomeTabs.entries.forEachIndexed { index, currentTab ->
                            Tab(
                                selected = selectedTabIndex.value == index,
                                selectedContentColor = Color.Red,
                                unselectedContentColor = MaterialTheme.colorScheme.outline,
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(currentTab.ordinal)
                                    }
                                },
                                text = { Text(text = currentTab.text) },
                                icon = {
                                    Icon(
                                        imageVector = if (selectedTabIndex.value == index)
                                            currentTab.selectedIcon else currentTab.unselectedIcon,
                                        contentDescription = "Tab Icon"
                                    )
                                }
                            )
                        }
                    }

                }

            }
        }

        Box(
            modifier = Modifier
                .padding(5.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(18.dp)
                )
                .aspectRatio(3f)
        ) {

        }
        Row {

        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun DetailsProductScreenPreview() {
    DetailsProductScreen()
}

enum class HomeTabs(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val text: String
) {
    Shop(
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart,
        text = "معرفی"
    ),
    Favourite(
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        text = "مشخصات"
    ),
}