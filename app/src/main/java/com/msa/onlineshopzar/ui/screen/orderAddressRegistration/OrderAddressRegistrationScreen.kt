package com.msa.onlineshopzar.ui.screen.orderAddressRegistration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.ui.screen.basket.ShoppingItem
import com.msa.onlineshopzar.ui.screen.detailsProduct.TopBarDetails
import com.msa.onlineshopzar.ui.screen.invoicepreview.ItemProductInvoice
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@Composable
fun OrderAddressRegistrationScreen() {

    Scaffold(modifier = Modifier
        .background(color = PlatinumSilver),
        topBar = {
            TopBarDetails(
                name = "ثبت نهایی سفارش",
            )
        }
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = Modifier
                    .background(color = PlatinumSilver)
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    painter = painterResource(id = R.drawable.stackicon),
                    contentDescription = "stack icon"
                )
                val l= listOf(
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
                    ShoppingItem("محصول ۲", "توضیحات محصول ۲", 2)
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

                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                )
                {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "iconbutton"
                    )
                    Text("ثبت سفارش")
                }

            }
        }
    }
}

@Preview
@Composable
private fun OrderAddressRegistrationScreenPreview() {
    OrderAddressRegistrationScreen()
}