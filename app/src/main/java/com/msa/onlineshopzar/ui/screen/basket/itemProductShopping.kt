package com.msa.onlineshopzar.ui.screen.basket

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.ui.theme.OnlineShopZarTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.data.local.entity.OrderEntity
import com.msa.onlineshopzar.ui.component.CounterButton
import com.msa.onlineshopzar.ui.theme.PlatinumSilver
import com.msa.onlineshopzar.ui.theme.lightcoral
import com.msa.onlineshopzar.utils.Currency
import kotlinx.coroutines.flow.MutableStateFlow

//itemName: String,
//itemDesc: String,
//itemCount: Int,
//onIncrease: () -> Unit,
//onDecrease: () -> Unit,
//onDelete: () -> Unit
@Composable
fun ShoppingCardItem(
    order: OrderEntity,
    viewModel: ShoppingViewModel = hiltViewModel()
) {

    // تابع برای محاسبه قیمت به‌روز شده



    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .size(100.dp, 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {


            var value1 by remember { mutableStateOf(0) }
            var value2 by remember { mutableStateOf(0) }

            val totalPrice by remember(value1, value2, order) {
                mutableStateOf(viewModel.calculateTotalPrice(value1, value2, order))
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .background(color = Color.White),
                    horizontalArrangement = Arrangement.SpaceAround,
                )
                {
                    Column(
                        modifier = Modifier
                            .aspectRatio(0.6f),
                        verticalArrangement = Arrangement.SpaceAround
                    ){
                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .size(100.dp, 75.dp)
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

                        Text(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                            ,
                            text = "قیمت هر عدد:"
                        )

                        order.salePrice?.let {
                            Text(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth(),
                                text = Currency(it).toFormattedString()
                            )
                        }

                    }

                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .aspectRatio(1.1f),
                        verticalArrangement = Arrangement.SpaceAround
                    ){
                        order.productName?.let { Text(text = it) }

                            Row(
                                modifier = Modifier
                                    .padding(vertical = 3.dp)
                                    .fillMaxWidth()
                                ,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                order.fullNameKala1?.let {
                                    Text(
                                        text = it,
                                        fontSize = 10.sp
                                    )
                                }
                                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                    CounterButton(
                                        value = order.numberOrder1.toString(),
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
                        order.fullNameKala2?.let {
                            check = 100f
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 3.dp)
                                .fillMaxWidth()
                                .alpha(check),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            order.fullNameKala2?.let {
                                Text(
                                    text = it,
                                    fontSize = 10.sp
                                )
                            }
                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                CounterButton(
                                    value = order.numberOrder2.toString(),
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


                        OutlinedButton(
                            onClick = { },
                            border = BorderStroke(1.dp, Color.Red),
                            shape = RoundedCornerShape(50), // = 50% percent
                            // or shape = CircleShape
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Red)
                        ) {
                            Text(
                                text = "مبلغ ناخالص:",
                                fontSize = 10.sp,
                                color= Color.White
                            )
                            Text(
                                text = totalPrice.toString(),
                                fontSize = 10.sp,
                                color= Color.White
                            )
                        }

                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(0.2f),
                        verticalArrangement = Arrangement.Center
                    ) {
                            Image(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(30.dp, 30.dp)
                                    .clickable {
                                         viewModel.deleteOrder(order.id)
                                    }
                                ,
                                imageVector = Icons.Default.Delete,
                                contentDescription = "product",
                            )

                    }

                }
                Canvas(Modifier.fillMaxSize()) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height
//                drawLine( //top line
//                    start = Offset(x = 0f, y = 0f),
//                    end = Offset(x = canvasWidth, y = 0f),
//                    strokeWidth = 3f,
//                    color = Color.Blue
//                )
//                drawLine( //bottom line
//                    start = Offset(x = 0f, y = canvasHeight),
//                    end = Offset(x = canvasWidth, y = canvasHeight),
//                    strokeWidth = 3f,
//                    color = Color.Green
//                )
//                drawLine( //left line
//                    start = Offset(x = 0f, y = 0f),
//                    end = Offset(x = 0f, y = canvasHeight),
//                    strokeWidth = 3f,
//                    color = Color.Magenta
//                )
                    drawLine( //right line
                        start = Offset(x = canvasWidth, y = 0f),
                        end = Offset(x = canvasWidth, y = canvasHeight),
                        strokeWidth = 10f,
                        color = Color.Red
                    )
                }
            }
        }
    }

}

@Composable
fun ShoppingCart(items: List<ShoppingItem>) {


    Column {
        items.forEach { item ->
            ShoppingCardItem(
                OrderEntity(
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
                    productImage = "",
                     numberOrder=33,
                     numberOrder1=44,
                     numberOrder2=33,
                     unitOrder="sh",
                )
            )
        }

        Button(
            onClick = { /* TODO: Handle place order action */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "قرار دادن سفارش")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShoppingCart(
        listOf(
            ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
            ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
            ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
            ShoppingItem("محصول ۱", "توضیحات محصول ۱", 1),
            ShoppingItem("محصول ۲", "توضیحات محصول ۲", 2)
        )
    )
}

data class ShoppingItem(val name: String, val desc: String, var count: Int)


// حتما ShoppingCartItem() را در داخل Scaffold یا ساختار Composable خود فراخوانی کنید.


// Remember to call `ShoppingCartItem()` inside your Scaffold or Composable view hierarchy.


//@Preview(showBackground = true)
//@Composable
//private fun ItemShoppingProductPreview() {
//    OnlineShopZarTheme {
//        LazyColumn(modifier = Modifier.fillMaxSize()){
//            item {
//                ShoppingCardItem()
//            }
//        }
//
//    }
//
//}