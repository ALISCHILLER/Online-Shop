@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.msa.onlineshopzar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog(message: String, onDismiss: (Boolean) -> Unit, hide:Boolean) {
    var hideDialog by remember { mutableStateOf(hide) }
    if (!hideDialog) {
        Dialog(
            onDismissRequest = { onDismiss(true) },
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        // Same action as in onDismissRequest
                        onDismiss(true)
                    }
            ) {
                Surface(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation,
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Error, // Icon for error
                            contentDescription = null, // Content description
                            tint = Color.Red, // Icon color
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "خطا", // Error text
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Cursive,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = message, // Error message
                            modifier = Modifier
                                .padding(10.dp)

                        )
                        OutlinedButton(
                            onClick = { hideDialog = true },
                            border = BorderStroke(1.dp, Color.Red),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Red)
                        ) {
                            Text(text = "بستن") // Close button text
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun DialogErrorStatePreview() {
    ErrorDialog("خطا", {},false)

}
