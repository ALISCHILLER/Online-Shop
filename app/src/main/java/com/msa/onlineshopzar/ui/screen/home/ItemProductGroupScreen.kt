package com.msa.onlineshopzar.ui.screen.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ItemProductGroupScreen(
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .width(80.dp) // تغییر در سایز عرض
            .height(80.dp) // تغییر در سایز ارتفاع
    ){
        Surface(
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .weight(1f) // تعیین وزن برای تقسیم فضای ردیف
                .padding(1.dp),
            color = Color.White
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize() // پر کردن اندازهٔ Surface
            ) {
                Icon(
                    imageVector = Icons.Default.AccessAlarm,
                    contentDescription = "icon product",
                    tint = Color.Red
                )

                Text(
                    text = "ماکارونی",
                    color = Color.Red
                )
            }
        }
    }
}


@Preview
@Composable
private fun ItemProductGroupScreenPreview() {
    ItemProductGroupScreen(
        onClick = { /* Do something on click */ },
    )
}