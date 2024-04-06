package com.msa.onlineshopzar.ui.screen.accountInformation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.ui.component.RoundedIconTextField

@Composable
fun ItemAccountInformation() {
    RoundedIconTextField(
        value = "username",
        onValueChange = { },
        label = "کد ملی",
        icon = Icons.Default.Person,
        modifier = Modifier.padding(6.dp),
        typeEnabled = false
    )
}

@Preview
@Composable
private fun ItemAccountInformationPreview() {
    ItemAccountInformation()
}