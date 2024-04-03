package com.msa.onlineshopzar.ui.screen.invoicepreview

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.R
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@Composable
fun ItemProductInvoice() {
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
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .background(color = Color.White),
                    horizontalArrangement = Arrangement.SpaceAround,
                )
                {
                    Column(
                        modifier = Modifier
                    ) {
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
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                        ,
                        verticalArrangement = Arrangement.SpaceAround,
                 
                    ) {
                        Row {
                            Text(text = "نام کالا :")
                            Text(text = "1.2 زشته 700 گرم زر")
                        }
                        Row {
                            Text(text = "تعداد سفارش:")
                            Text(text = "13 کارتن و 2 عدد")
                        }
                        Row {
                            Text(text = "فی:")
                            Text(text = "12,135,450")
                        }
                        Row {
                            Text(text = "تخفیفات:")
                            Text(text = "12,135,450")
                        }
                        Row {
                            Text(text = "مالیات:")
                            Text(text = "12,135,450")
                        }
                        Row {
                            Text(text = "مبلغ ثابل پرداخت:")
                            Text(text = "12,135,450")
                        }
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

@Preview
@Composable
private fun ItemProductInvoicePreview() {
    ItemProductInvoice()
}