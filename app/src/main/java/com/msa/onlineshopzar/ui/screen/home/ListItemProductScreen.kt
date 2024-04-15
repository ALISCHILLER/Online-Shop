package com.msa.onlineshopzar.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.data.local.entity.OrderEntity
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.ui.component.CounterButton
import com.msa.onlineshopzar.ui.theme.PlatinumSilver
import com.msa.onlineshopzar.utils.Currency

@Composable
fun ListItemProductScreen(
    productModelEntity: ProductModelEntity,
    viewModel: HomeViewModel = hiltViewModel(),
    orderEntity: List<OrderEntity>?
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {

            val productId = productModelEntity.id
            val orderItem = orderEntity?.firstOrNull { it.id == productId }
            var value1 by remember { mutableStateOf(orderItem?.numberOrder1 ?: 0) }
            var value2 by remember { mutableStateOf(orderItem?.numberOrder2 ?: 0) }
            // تابع برای محاسبه قیمت به‌روز شده
            val totalPrice by remember(value1, value2, productModelEntity) {
                mutableStateOf(viewModel.calculateTotalPrice(value1, value2, productModelEntity))
            }
            Surface(
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                color = Color.White
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = PlatinumSilver,
                                shape = RoundedCornerShape(18.dp)
                            )
                            .aspectRatio(1f)
                    ) {

//                        Image(
//                            painter = painterResource(id = R.drawable.product),
//                            contentDescription = "product",
//                            modifier = Modifier.fillMaxSize()
//                        )
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = productModelEntity.productImage,
                            placeholder = painterResource(id = R.drawable.no_image_placeholder) ,
                            contentDescription = "productImage"
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(6.dp)
                                .background(Color.Red, shape = RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = Currency(productModelEntity.salePrice)
                                    .toFormattedString(),
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }

                    productModelEntity.productName?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(vertical = 8.dp)

                        )
                    }



                    Column(modifier = Modifier.padding(5.dp)) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 3.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            productModelEntity.fullNameKala1?.let {
                                Text(
                                    text = it,
                                    fontSize = 10.sp
                                )
                            }
                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                CounterButton(
                                    value = value1.toString(),
                                    onValueIncreaseClick = {
                                        value1 += 1
                                    },
                                    onValueDecreaseClick = {
                                        value1 = maxOf(value1 - 1, 0)
                                    },
                                    onValueClearClick = {
                                        value1 = 0
                                    }
                                )
                            }
                        }

                        var check by remember { mutableStateOf(0f) }
                        productModelEntity.fullNameKala2?.let {
                            check = 100f
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 3.dp)
                                .fillMaxWidth()
                                .alpha(check),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            productModelEntity.fullNameKala2?.let {
                                Text(
                                    text = it,
                                    fontSize = 10.sp
                                )
                            }
                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                CounterButton(
                                    value = value2.toString(),
                                    onValueIncreaseClick = {
                                        value2 += 1
                                    },
                                    onValueDecreaseClick = {
                                        value2 = maxOf(value2 - 1, 0)
                                    },
                                    onValueClearClick = {
                                        value2 = 0
                                    }
                                )
                            }
                        }


                        Row(
                            modifier = Modifier
                                .padding(vertical = 3.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "مبلغ ناخالص:",
                                fontSize = 10.sp
                            )

                            Text(
                                text = Currency(totalPrice.toString()).toFormattedString(),
                                fontSize = 12.sp
                            )

                        }
                    }


                }
            }
        }
    }

}


@Preview
@Composable
private fun ListItemProductScreenPreview() {
    ListItemProductScreen(
        ProductModelEntity(
            "11",
            convertFactor1 = 1,
            convertFactor2 = 12,
            fullNameKala1 = "biscuit (1)",
            fullNameKala2 = "biscuit (2)",
            productCode = 659985,
            productGroupCode = 54544,
            productName = "biscuit",
            unit1 = "shelf",
            unit2 = "Carton",
            unitid1 = "54654",
            unitid2 = "4565",
            salePrice = "98565",
            productImage = ""
        ),
        orderEntity = null
    )
}

// تابع برای محاسبه قیمت به‌روز شده
//private fun calculateTotalPrice(value1: Int, value2: Int, productModelEntity: ProductModelEntity): Float {
//    val valueNumber = (value2 * productModelEntity.convertFactor2)+value1
//    val salePrice =valueNumber * productModelEntity.salePrice.toFloat()
//    return salePrice
//}