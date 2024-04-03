package com.msa.onlineshopzar.ui.screen.invoicepreview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.ui.theme.OnlineShopZarTheme
import com.msa.onlineshopzar.ui.theme.PlatinumSilver

@Composable
fun ItemTopBarDetails(
    name:String,
    value:String
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp, 60.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Box(
                Modifier
                    .background(color = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(color = Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(text = name)
                    Text(text = value)
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


@Preview(showBackground = true)
@Composable
private fun ItemTopBarDetailsPreciew() {
    OnlineShopZarTheme {
        Column(
            modifier = Modifier
                .background(color = PlatinumSilver)
                .fillMaxSize()
        ){
            Row(
                modifier = Modifier
                    .background(color = PlatinumSilver)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                ItemTopBarDetails(
                    "راس عرف",
                    "11,249,203"
                )
                ItemTopBarDetails(
                    "راس عرف",
                    "11,249,203"
                )
            }
        }
    }

}