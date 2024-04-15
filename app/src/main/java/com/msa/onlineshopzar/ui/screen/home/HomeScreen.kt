package com.msa.onlineshopzar.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msa.onlineshopzar.data.local.entity.ProductGroupEntity
import com.msa.onlineshopzar.ui.component.DialogLoadingWait
import com.msa.onlineshopzar.ui.theme.*

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    var selectedProductGroup by remember { mutableStateOf<ProductGroupEntity?>(null) }

    LaunchedEffect(Unit){
        viewModel.productCheck()
    }


    val allTasks by viewModel.allTasks.collectAsState()
    val allOrder by viewModel.allOrder.collectAsState()
    val user by viewModel.user.collectAsState()
    val allProductGroup by viewModel.allProductGroup.collectAsState()
    val homeState by viewModel.state.collectAsState()

    val currentSample = rememberSaveable { mutableStateOf<Boolean?>(null) }
    val onReset = { currentSample.value = homeState.isLoading }

    if (homeState.isLoading) {
        DialogLoadingWait(onReset)
    }






    Scaffold(
        modifier = Modifier
            .background(color = PlatinumSilver),
        topBar = {
            user?.let { HomeTopBar(it) }
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
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items(allProductGroup) { productGroup ->
                        ItemProductGroupScreen(
                            productGroup,
                            onClick = { selectedProductGroup = it },
                            isSelected = selectedProductGroup == productGroup
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f),
                ) {
                    items(allTasks) {
                        ListItemProductScreen(
                            it,
                            orderEntity =  allOrder
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}