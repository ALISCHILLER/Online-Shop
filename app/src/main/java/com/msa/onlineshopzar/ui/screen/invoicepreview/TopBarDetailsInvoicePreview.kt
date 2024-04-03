package com.msa.onlineshopzar.ui.screen.invoicepreview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun TopBarDetailsInvoicePreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                ItemTopBarDetails(
                    "راس عرف",
                    "25"
                )
                ItemTopBarDetails(
                    "راس چک",
                    "19"
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                ItemTopBarDetails(
                    "مبلغ آنی",
                    "11,249,203"
                )
                ItemTopBarDetails(
                    "مبلغ چک",
                    "11,249,203"
                )
                ItemTopBarDetails(
                    "مبلغ چک عرفی ",
                    "11,249,203"
                )
            }
        }
    }
}


@Preview
@Composable
private fun TopBarDetailsInvoice() {
    TopBarDetailsInvoicePreview()
}