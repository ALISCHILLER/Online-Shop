package com.msa.onlineshopzar.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.data.local.entity.ProductModelEntity
import com.msa.onlineshopzar.ui.component.CounterButton
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@Composable
fun ListItemProductScreen(
    productModelEntity: ProductModelEntity
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var value1 by remember { mutableStateOf(0) }
        var value2 by remember { mutableStateOf(0) }


        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
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
                        Image(
                            painter = painterResource(id = R.drawable.product),
                            contentDescription = "product",
                            modifier = Modifier.fillMaxSize()
                        )
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
                            check=100f
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 3.dp)
                                .fillMaxWidth()
                                .alpha(check)
                            ,
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
                            productModelEntity.salePrice?.let {
                                Text(
                                    text = it,
                                    fontSize = 12.sp
                                )
                            }
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
             convertFactor1 = 12,
             convertFactor2 = 12,
             fullNameKala1 = "sad",
             fullNameKala2 = null,
             productCode = 659985,
             productGroupCode = 54544,
             productName = "jghhjighj",
             unit1 ="sadsad",
             unit2 = "sadasd",
             unitid1="54654",
             unitid2 = "4565",
             salePrice = "12354354",
             productImage = ""
         )
     )
}