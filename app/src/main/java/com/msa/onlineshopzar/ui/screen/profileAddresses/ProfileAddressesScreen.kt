package com.msa.onlineshopzar.ui.screen.profileAddresses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.msa.onlineshopzar.ui.screen.basket.ShoppingItem
import com.msa.onlineshopzar.ui.screen.detailsProduct.TopBarDetails
import com.msa.onlineshopzar.ui.screen.orderAddressRegistration.ItemAddressCustomer
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@Composable
fun ProfileAddressesScreen() {
    Scaffold(
        modifier = Modifier
            .background(color = PlatinumSilver),
        topBar = {
            TopBarDetails("آدرس ها")
        }
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .background(color = PlatinumSilver)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween

            )
            {
                val l= listOf(
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                )
                LazyColumn(
                    modifier = Modifier
                        .weight(1f) // اینجا از وزن استفاده شده است تا LazyColumn بیشتر از فضای دیگری اشغال کند
                        .fillMaxWidth(),
                ){
                    items(l){
                        ItemAddressCustomer()
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun ProfileAddressesPreview() {
    ProfileAddressesScreen()
}