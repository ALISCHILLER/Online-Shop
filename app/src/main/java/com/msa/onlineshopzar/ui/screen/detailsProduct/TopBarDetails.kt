package com.msa.onlineshopzar.ui.screen.detailsProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.ui.theme.PlatinumSilver


@Composable
fun TopBarDetails(
    name:String,
    details:String=""
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .background(color = PlatinumSilver)
                .padding(8.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                Text(text = name)
                if (details.isNotEmpty())
                    Text(
                        text = details,
                        color = Color.Red
                    )
            }
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .padding(9.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew
                    , contentDescription = "ArrowBack"
                )
            }
        }
    }
}


@Preview
@Composable
private fun TopBarDetailsPreview() {
    TopBarDetails(
        "جزِئیات محصول"
    )
}