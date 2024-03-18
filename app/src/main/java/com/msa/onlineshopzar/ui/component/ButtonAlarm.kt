package com.msa.onlineshopzar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonAlarm(
    icon: ImageVector,
    onClick: () -> Unit,
    hasMessage: Boolean
) {
    var notificationReceived by remember { mutableStateOf(true) }

    Box(
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.size(62.dp)
        ) {
            Icon(
                modifier = Modifier,
                imageVector = icon,
                contentDescription = "Phone Icon",
                tint= Color.Black
            )
        }

        if (notificationReceived) {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
                    .background(Color.Red, shape = CircleShape),
                contentAlignment = Alignment.Center

            ) {
               Text(
                   text = "2",
                   color = Color.White
               )
            }
        }
    }
}

@Preview
@Composable
private fun ButtonAlarmPreview() {
    ButtonAlarm(
        icon = Icons.Default.Notifications,
        onClick = { /* Do something on click */ },
        hasMessage = true
    )
}