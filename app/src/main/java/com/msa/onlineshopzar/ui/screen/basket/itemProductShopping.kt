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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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


@Composable
fun ShoppingCardItem(
    itemName: String,
    itemDesc: String,
    itemCount: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .size(100.dp, 75.dp)
            .drawWithCache {
                onDrawWithContent {
                    // draw behind the content the vertical line on the left
                    drawLine(
                        color = Color.Red,
                        start = Offset.Zero,
                        end = Offset(0f, this.size.height),
                        strokeWidth= 10f
                    )

                    // draw the content
                    drawContent()
                }
            }
        ,

    ) {

    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .size(100.dp, 75.dp)
            .drawWithCache {
                onDrawWithContent {
                    // draw behind the content the vertical line on the left
                    drawLine(
                        color = Color.Red,
                        start = Offset.Zero,
                        end = Offset(0f, this.size.height),
                        strokeWidth= 10f
                    )

                    // draw the content
                    drawContent()
                }
            }
            .background(Color.Blue, shape = RoundedCornerShape(12.dp))
    ) {

    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .size(100.dp, 75.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            Column {
                //your content
            }
            Canvas(Modifier.fillMaxSize()) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawLine( //top line
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = canvasWidth, y = 0f),
                    strokeWidth = 3f,
                    color = Color.Blue
                )
                drawLine( //bottom line
                    start = Offset(x = 0f, y = canvasHeight),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    strokeWidth = 3f,
                    color = Color.Green
                )
                drawLine( //left line
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = 0f, y = canvasHeight),
                    strokeWidth = 3f,
                    color = Color.Magenta
                )
                drawLine( //right line
                    start = Offset(x = canvasWidth, y = 0f),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    strokeWidth = 10f,
                    color = Color.Red
                )
            }
        }
    }

    val colorBg = Color(0xFF2C3141)
    val colors =
        listOf(
            Color(0xFFFF595A),
            Color(0xFFFFC766),
            Color(0xFF35A07F),
            Color(0xFF35A07F),
            Color(0xFFFFC766),
            Color(0xFFFF595A)
        )

    val brush = Brush.linearGradient(colors)

    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp).background(colorBg)) {
        drawRoundRect(
            brush = brush,
            cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx()
            )
        )

        drawRoundRect(
            color = colorBg,
            topLeft = Offset(1.dp.toPx(), 1.dp.toPx()),
            size = Size(
                width = size.width - 2.dp.toPx(),
                height = size.height - 2.dp.toPx()
            ),
            cornerRadius = CornerRadius(
                x = 19.dp.toPx(),
                y = 19.dp.toPx()
            )
        )
    }

}

@Composable
fun ShoppingCart(items: List<ShoppingItem>) {
    Column {
        items.forEach { item ->
            ShoppingCardItem(
                itemName = item.name,
                itemDesc = item.desc,
                itemCount = item.count,
                onIncrease = { /* TODO: Handle increase action */ },
                onDecrease = { /* TODO: Handle decrease action */ },
                onDelete = { /* TODO: Handle delete action */ }
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