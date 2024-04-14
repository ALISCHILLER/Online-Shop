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
import com.msa.onlineshopzar.data.local.entity.ProductGroupEntity


import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ItemProductGroupScreen(
    productGroupEntity: ProductGroupEntity,
    onClick: (ProductGroupEntity) -> Unit,
    isSelected: Boolean,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var isSelectedState by remember { mutableStateOf(isSelected) }

    val iconTint = if (isSelected) Color.White else Color.Red
    val textBackground = if (isSelected) Color.Red else Color.White

    Row(
        modifier = Modifier
            .padding(10.dp)
            .width(80.dp)
            .height(80.dp)
            .clickable {
                onClick(productGroupEntity)
                viewModel.getProduct(productGroupEntity)
            }
    ) {
        Surface(
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .weight(1f)
                .padding(1.dp),
            color = textBackground
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                ProductGroupIcon(icon = Icons.Default.AccessAlarm, tint = iconTint)
                Text(
                    text = productGroupEntity.productGroup,
                    color = iconTint
                )
            }
        }
    }
}


@Composable
fun ProductGroupIcon(icon: ImageVector, tint: Color) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint // تغییر رنگ آیکون به رنگ مورد نظر
    )
}




@Preview
@Composable
private fun ItemProductGroupScreenPreview() {
    ItemProductGroupScreen(
        ProductGroupEntity(
            1,
            "آرد"
        ),
        onClick = { /* Do something on click */ },
        false
    )
}