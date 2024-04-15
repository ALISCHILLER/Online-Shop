package com.msa.onlineshopzar.ui.screen.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.msa.onlineshopzar.ui.component.RadioGroup
import com.msa.onlineshopzar.ui.navigation.navgraph.Route
import com.msa.onlineshopzar.ui.screen.detailsProduct.TopBarDetails
import com.msa.onlineshopzar.ui.screen.home.HomeViewModel
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@Composable
fun ShoppingListScreen(
    navController: NavController,
    viewModel: ShoppingViewModel = hiltViewModel()
) {


    Scaffold(
        modifier = Modifier
            .background(color = PlatinumSilver),
        topBar = {
            TopBarDetails("لیست خرید های شما")
        },
        bottomBar = {

        }
    ) {

        LaunchedEffect(Unit){
            viewModel.getAllOrder()
        }

        val allTasks by viewModel.allOrder.collectAsState()
        var selectedOption by remember { mutableStateOf("عرفی") }
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(color = PlatinumSilver)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {

                LazyColumn(
                    modifier = Modifier
                        // اینجا از وزن استفاده شده است تا LazyColumn بیشتر از فضای دیگری اشغال کند
                        .fillMaxWidth()
                        .weight(1.0f), // پر کردن عرض موجود در طول
                ) {
                    items(allTasks) {
                        ShoppingCardItem(it)
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                    )
                {
                    // Replace the Row containing the RadioGroup with your custom RadioGroup component
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        RadioGroup(
                            options = listOf("عرفی", "چک", "نقدی"),
                            selectedOption = selectedOption,
                            onOptionSelected = { selectedOption = it }
                        )
                    }

                }

                Button(
                    onClick = {
                        navController.navigate(Route.InvoicePreviewScreen.route)
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "iconbutton"
                    )
                    Text("ثبت پیش فاکتور")
                }

            }

        }
    }
}

@Preview
@Composable
private fun ShopppingListPreview() {
    val navController = rememberNavController()
    ShoppingListScreen(navController)
}