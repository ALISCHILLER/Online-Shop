package com.msa.onlineshopzar.ui.screen.basket

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
            .padding(8.dp),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = itemName, style = MaterialTheme.typography.bodySmall)
            Text(text = itemDesc, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
                Text(text = "$itemCount")
                IconButton(onClick = onDecrease) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease")
                }
                IconButton(onClick = onIncrease) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Increase")
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