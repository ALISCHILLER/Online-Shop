package com.msa.onlineshopzar.ui.screen.profileRestPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.msa.onlineshopzar.ui.component.RoundedIconTextField
import com.msa.onlineshopzar.ui.screen.detailsProduct.TopBarDetails
import com.msa.onlineshopzar.ui.theme.*

@Composable
fun ProfileRestPasswordScreen() {
    var password by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var repeatNewPassword by remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier
            .background(color = PlatinumSilver),
        topBar = {
            TopBarDetails("تغییر رمز عبور")
        }
    )
    {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .background(color = PlatinumSilver)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween

            )
            {
                Column(
                    modifier = Modifier
                        .width(328.dp)
                        .height(490.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    RoundedIconTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "رمزعبور فعلی",
                        icon = Icons.Default.Lock,
                        isPassword = true,
                        modifier = Modifier.padding(6.dp)
                    )
                    RoundedIconTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = "رمزعبورجدید",
                        icon = Icons.Default.Lock,
                        isPassword = true,
                        modifier = Modifier.padding(6.dp)
                    )
                    RoundedIconTextField(
                        value = repeatNewPassword,
                        onValueChange = { repeatNewPassword = it },
                        label = "تکراررمزعبور",
                        icon = Icons.Default.Lock,
                        isPassword = true,
                        modifier = Modifier.padding(6.dp)
                    )
                    Button(
                        onClick = {
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    )
                    {
                        Text("ثبت ")
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun ProfileRestPasswordScreenPreview() {
    ProfileRestPasswordScreen()
}